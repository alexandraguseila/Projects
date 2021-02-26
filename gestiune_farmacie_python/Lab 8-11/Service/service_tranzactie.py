from Repository.repository_generic import *
from Domain.medicament import Medicament
from Domain.card import Card
from Domain.tranzactie import Tranzactie
import random


class Service_Tranzactie:
    def __init__(self, repository, repository_medicament, repository_card):
        self.__repository = repository
        self.__repository1 = repository_medicament
        self.__repository2 = repository_card
        self.__undo_operations = []
        self.__redo_operations = []
        self.__redo_count = 0
        self.__reserve_action = []

    def add_tranzactie(self, id_tranzactie, id_medicament, id_card_t, nr_bucati, data_ora):
        """
        Adauga o tranzactie
        :param id_tranzactie: int, id
        :param id_medicament: int, id
        :param id_card_t: int, id
        :param nr_bucati: int, nr
        :param data_ora: str, data
        :return:
        """
        # lista_reduceri = []
        med = False
        for medicament in self.__repository1.read():
            if medicament.get_id() == id_medicament:
                med = True
        crd = False
        for card in self.__repository2.read():
            if card.get_id() == id_card_t:
                crd = True

        '''for card in self.__repository_card.read_card():
            if card.get_id_card() == id_card_t and id_card_t != 0:
                for medicament in self.__repository_medicament.read_medicamente():
                    if medicament.get_id() == id_medicament and medicament.get_prescription() == 'Da':
                        pret = medicament.get_price() - 15 * medicament.get_price() / 100
                        medicament.set_price(pret)
                        lista_reduceri.append([medicament.get_price(), 'Reducere 15%  '])
                    if medicament.get_id() == id_medicament and medicament.get_prescription() == 'Nu':
                        pret = medicament.get_price() - 10 * medicament.get_price() / 100
                        medicament.set_price(pret)
                        lista_reduceri.append([medicament.get_price(), 'Reducere 10%  '])'''

        if med == False or crd == False:
            raise ValueError('TR. ESUATA')
        # if id_card_t != 0:

        tranzactie = Tranzactie(id_tranzactie, id_medicament, id_card_t, nr_bucati, data_ora)
        self.__repository.add(tranzactie)
        self.__undo_operations.append(lambda: self.__repository.delete(id_tranzactie))
        self.__redo_operations.append(lambda: self.__repository.add(tranzactie))

        '''if len(lista_reduceri) == 0:
            print('Nu s-au aplicat reduceri')
        else:
            print(lista_reduceri)'''

    def update_tranzactie(self, id_tranzactie, id_medicament, id_card_t, nr_bucati, data_ora):
        """
        Modifica o tranzactie
        :param id_tranzactie: int, id
        :param id_medicament: int, id
        :param id_card_t: int, id
        :param nr_bucati: int, nr
        :param data_ora: str, data
        :return:
        """
        for tr in self.get_all():
            if id_tranzactie == tr.get_id():
                tr_undo = Tranzactie(tr.get_id(), tr.get_id_medicament(), tr.get_id_card_t(),
                                     tr.get_nr_bucati(), tr.get_data_ora())
                break
        tranzactie = Tranzactie(id_tranzactie, id_medicament, id_card_t, nr_bucati, data_ora)
        self.__repository.update(tranzactie)
        self.__undo_operations.append(lambda: self.__repository.update(tr_undo))
        self.__redo_operations.append(lambda: self.__repository.update(tranzactie))

    def remove_tranzactie(self, id):
        """
        Sterge o tranzactie
        :param id: int, id
        :return:
        """
        listaa = self.__repository.read()
        for tranzactie in listaa:
            if tranzactie.get_id() == id:
                self.__repository.delete(id)
                self.__undo_operations.append(lambda: self.__repository.create(tranzactie))
                self.__redo_operations.append(lambda: self.__repository.delete(id))
                return

    def remove_tranzactie_card(self, id):
        """
        Sterge o tranzactie
        :param id: int, id
        :return:
        """
        listaa = self.__repository.read()
        for tranzactie in listaa:
            if tranzactie.get_id_card_t() == id:
                self.__repository.delete(tranzactie.get_id())
                return

    def remove_tranzactie_medicament(self, id):
        """"
        Sterge o tranzactie
        :param id: int, id
        :return:
        """
        listaa = self.__repository.read()
        for tranzactie in listaa:
            if tranzactie.get_id_medicament() == id:
                self.__repository.delete(tranzactie.get_id())
                '''##
                for medicament in self.__repository_medicament.read_medicamente():
                    if tranzactie.get_id_medicament() == medicament.get_id():
                        self.__repository_medicament.delete_medicament(medicament)
                for card in self.__repository_card.read_carduri():
                    if tranzactie.get_id_card_t() == card.get_id_card():
                        self.__repository_card.delete_card(card)
                #self.__repository_card.delete_card(tranzactie.get_id_card_t())'''
                return

    def get_all(self):
        """
        Preia toate tranzactiile
        :return:
        """
        return self.__repository.read()

    def __get_med_nr_bucati(self, tranzactii):
        """
        Creeaza un dict de nr buc med
        :param tranzactii: class, tranzactii
        :return:
        """
        dict_med = {}
        for tranzactie in tranzactii:
            med_id = tranzactie.get_id_medicament()
            numar = tranzactie.get_nr_bucati()
            dict_med[med_id] = numar
        return dict_med

    def sorted_med_by_nr_bucati(self):
        """
        Sorteaza tranzactiile
        :return: tranzactiile tranzactionate
        """
        meds = self.__repository1.read()
        tranzactii = self.__repository.read()
        numar_buc = self.__get_med_nr_bucati(tranzactii)
        return sorted(meds, key=lambda medicament: numar_buc[medicament.get_id()])

    def interval(self, d1, d2):
        """
        Afiseaza tr. intr un interval
        :param d1: int, ziua 1
        :param d2: int, ziua 2
        :return:
        """
        listaa = self.__repository.read()
        for tranzactie in listaa:
            data = tranzactie.get_data_ora()
            ziua = data.split('.')
            if d1 < int(ziua[0]) < d2:
                print(tranzactie)

    def stergere_int(self, d1, d2):
        """
        Sterge o tranzactie din intervalul de zile
        :param d1: int, ziua 1
        :param d2: int, ziua 2
        :return:
        """
        listaa = self.__repository.read()
        for tranzactie in listaa:
            data = tranzactie.get_data_ora()
            ziua = data.split('.')
            if d1 < int(ziua[0]) < d2:
                ID = tranzactie.get_id()
                self.remove_tranzactie(ID)

    def populate(self):
        """
        Populeaza o tranzactie
        :return:
        """
        id_tranzactie = random.randint(1, 100000)
        id_medicament = random.randint(1, 100000)
        id_card_t = random.randint(1, 100000)
        nr_bucati = random.randint(0, 1000)
        zi = random.randint(1, 28)
        luna = random.randint(1, 12)
        an = random.randint(1970, 2018)
        ora = random.randint(1, 24)
        minute = random.randint(1, 59)
        data_ora = '{}.{}.{};{}:{}'.format(zi, luna, an, ora, minute)
        self.add_tranzactie(id_tranzactie, id_medicament, id_card_t, nr_bucati, data_ora)

    def get_tranzactie(self, id):
        """
        Preia o tranzactie dupa un anumit id
        :param id: int, id-ul unei tranzactii
        :return: class, o tranzactie
        """
        return self.__repository.read(id)


'''def test_tranzactie_service():
    """
    Testeaza repository Tranzactie
    :return:
    """
    repo_tranzactie = Repository_tranzactie
    repo_medicament = Repository_medicament
    repo_card = Repository_card
    r = Service_Tranzactie(repo_tranzactie, repo_medicament, repo_card)
    v1 = Tranzactie(1, 5, 4, 65, '52/58/58-12:12')
    v2 = Tranzactie(2, 3, 44, 635, '52/58/58-12:12')
    r.add_tranzactie(1, 5, 4, 65, '52/58/58-12:12')
    r.add_tranzactie(2, 3, 44, 635, '52/58/58-12:12')
    assert len(r.get_all()) == 2
    r.update_tranzactie(2, 3, 44, 635, '52/58/58-12:12')
    r.remove_tranzactie(1)
    assert len(r.get_all()) == 1
    assert r.get_all() == None
    
# test_tranzactie_service()
'''