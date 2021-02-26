from Domain.card import *
import string
import random


class Service_Card:

    def __init__(self, repository):
        self.__repository = repository
        self.__undo_operations = []
        self.__redo_operations = []
        self.__redo_count = 0
        self.__reserve_action = []

    def add_card(self, id_card, name_person, prenume_person, CNP, data_nasterii, data_inregistrarii):
        """
        Adauga un card
        :param id_card: int, id
        :param name_person: str, nume
        :param prenume_person: str, prenume
        :param CNP: str, cnp
        :param data_nasterii: str, data nasterii
        :param data_inregistrarii: str, data nasterii
        :return:
        """
        card = Card(id_card, name_person, prenume_person, CNP, data_nasterii, data_inregistrarii)
        self.__repository.add(card)
        self.__undo_operations.append(lambda: self.__repository.delete(id_card))
        self.__redo_operations.append(lambda: self.__repository.add(card))

    def update_card(self, id_card, name_person, prenume_person, CNP, data_nasterii, data_inregistrarii):
        """
        Modifica un card
        :param id_card: int, id
        :param name_person: str, nume
        :param prenume_person: str, prenume
        :param CNP: str, cnp
        :param data_nasterii: str, data nasterii
        :param data_inregistrarii: str, data nasterii
        :return: noul card
        """
        for card in self.get_all():
            if id_card == card.get_id():
                card_undo = Card(card.get_id(), card.get_name_person(), card.get_prenume_person(), card.get_CNP(),
                                 card.get_data_nasterii(), card.get_data_inregistrarii())
                break
        card = Card(id_card, name_person, prenume_person, CNP, data_nasterii, data_inregistrarii)
        self.__repository.update(card)
        self.__undo_operations.append(lambda: self.__repository.update(card_undo))
        self.__redo_operations.append(lambda: self.__repository.update(card))

    def remove_card(self, id):
        """
        Sterge un card
        :param id: int, id
        :return:
        """
        lista = self.__repository.read()
        for card in lista:
            if card.get_id() == id:
                self.__repository.delete(id)
                self.__undo_operations.append(lambda: self.__repository.add(card))
                self.__redo_operations.append(lambda: self.__repository.delete(id))
                return
        self.__repository.delete(id)

    def get_all(self):
        """
        Preia toate entitatile card
        :return: Toate entitatile card
        """
        return self.__repository.read()

    def cautare_by_name(self, NAME):
        """
        Cauta un card dupa nume
        :param NAME: str, nume
        :return:
        """
        listaa = self.__repository.read()
        lista = []
        for card in listaa:
            if card.get_name_person() == NAME:
                lista.append(card)
        return lista

    def cautare_by_prenume(self, PRENUME):
        """
        Cauta un card dupa prenume
        :param PRENUME: str, prenume
        :return:
        """
        listaa = self.__repository.read()
        lista = []
        for card in listaa:
            if card.get_prenume_person() == PRENUME:
                lista.append(card)
        return lista

    def cautare_by_CNP(self, CNP):
        """
        Cauta un card dupa cnp
        :param CNP: str, cnp
        :return:
        """
        listaa = self.__repository.read()
        lista = []
        for card in listaa:
            if card.get_CNP() == CNP:
                lista.append(card)
        return lista

    def cautare_by_data_nasterii(self, DATA_NASTERII):
        """
        CAUTA UN CARD DUPA DANA NASTERII
        :param DATA_NASTERII: str, data nasterii
        :return:
        """
        listaa = self.__repository.read()
        lista = []
        for card in listaa:
            if card.get_data_nasterii() == DATA_NASTERII:
                lista.append(card)
        return lista

    def cautare_by_data_inregistrarii(self, DATA_INREGISTRARII):
        """
        Cauta un card dupa data inregistrarii
        :param DATA_INREGISTRARII: str, data inregistrarii
        :return:
        """
        listaa = self.__repository.read()
        lista = []
        for card in listaa:
            if card.get_data_inregistrarii() == DATA_INREGISTRARII:
                lista.append(card)
        return lista

    def get_card(self, id):
        """
        Preia un card dupa un anumit id
        :param id: int, id-ul unui card
        :return: class, un card
        """
        return self.__repository.read(id)

    def populate(self):
        """
        Populeaza entitatea
        :return:
        """
        letters = string.ascii_lowercase
        id_card = random.randint(1, 100000)
        name_person = ''.join(random.choice(letters) for i in range(10))
        prenume_person = ''.join(random.choice(letters) for i in range(10))
        CNP = random.randint(1000000000000, 700000000000)
        zi = random.randint(1, 28)
        luna = random.randint(1, 12)
        an = random.randint(1970, 2018)
        data_nasterii = '{}.{}.{}'.format(zi, luna, an)
        zi1 = random.randint(1, 28)
        luna1 = random.randint(1, 12)
        an1 = random.randint(1970, 2018)
        data_inregistrarii = '{}.{}.{}'.format(zi1, luna1, an1)
        self.add_card(id_card, name_person, prenume_person, CNP, data_nasterii, data_inregistrarii)

    def undo_c(self):
        if len(self.__undo_operations) > 0:
            undo_op = self.__undo_operations.pop()
            undo_op()
            self.__redo_count += 1
            self.__reserve_action.append(lambda: undo_op())
        else:
            print('NU UNDO C')

    def redo_c(self):
        if self.__redo_count > 0:
            self.__redo_count -= 1
            redo = self.__redo_operations.pop()
            redo()
            reserve_action = self.__reserve_action.pop()
            self.__redo_operations.append(lambda: reserve_action())
            self.__redo_operations.append(lambda: redo())
        else:
            raise ValueError("Nu se poate face redo")

    def get_id_prenume(self):
        listaa = self.get_all()
        lista = []
        for card in listaa:
            lista.append([card.get_id(), card.get_prenume_person(), card.get_name_person(), card.get_CNP(),
                          card.get_data_inregistrarii()])
        return lista

    def permutari(self, n):
        results = []
        lista = self.get_id_prenume()

        def inner(permutare_curenta):
            if len(permutare_curenta) == n:
                results.append(permutare_curenta)
                return
            for card in lista:
                if card not in permutare_curenta:
                    inner(permutare_curenta + [card])

        inner([])
        return results

    def add_permutare(self):
        lista = []
        for p in self.permutari(len(self.get_all())):
            lista.append(p)
        return lista

    def my_sorted_quicksort(self, lista, key=lambda x: x, reverse=False):
        """
        Implements my own sorted!
        """
        if not lista:
            return []
        else:
            pivot = lista[0]
            greater_than_pivot = [nr for nr in lista if key(nr) > pivot]

            equal_to_pivot = [nr for nr in lista if key(nr) == pivot]
            less_than_pivot = [nr for nr in lista if key(nr) < pivot]
            return self.my_sorted_quicksort(greater_than_pivot, key) + \
                   equal_to_pivot + self.my_sorted_quicksort(less_than_pivot, key)

'''    def test_permutare(self):
        card1 = Card(1, 'Zara', 'Paul', '14.01.2015')
        card2 = Card(2, 'Popa', 'Maria', '6000630082458', '05.05.2018')
        card3 = Card(3, 'Lazea', 'Camelia', '554123154545', '14.03.2014;15:40')
        lista = [card1, card2, card3]
        list = self.add_permutare()
        for o in list:
            print(o)
    test_permutare()'''



'''def test_card_service():
    """
    Testeaza repository Tranzactie
    :return:
    """
    repo_card = Repository_card
    r = Service_Card(repo_card)
    v1 = Card(1, 5, 4, 65, '52/58/58-12:12', '44')
    v2 = Card(2, 3, 44, 635, '52/58/58-12:12', '54')
    r.add_card(1, 5, 4, 65, '52/58/58-12:12', '44')
    r.add_card(2, 3, 44, 635, '52/58/58-12:12', '454')
    assert len(r.get_all()) == 2
    r.update_card(2, 3, 44, 635, '52/58/58-12:12', '343')
    r.remove_card(1)
    assert len(r.get_all()) == 1
    assert r.get_all() == None'''
# test_card_service()
