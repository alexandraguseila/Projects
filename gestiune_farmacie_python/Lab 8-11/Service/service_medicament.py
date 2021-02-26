from Domain.medicament import Medicament
import string
import random


class Service_Medicament:
    def __init__(self, repository):
        self.__repository = repository

    def add_medicament(self, id, name, producer, price, prescription):
        """
        Adauga un medicament
        :param id: int, id
        :param name: str, nume
        :param producer: str, producer
        :param price: int, pret
        :param prescription: str, prescription
        :return:
        """

        if price <= 0:
            raise ValueError('PRETUL NU POATE FI <= 0')
        if prescription == 'Da' or prescription == 'Nu':
            medicament = Medicament(id, name, producer, price, prescription)
            self.__repository.add(medicament)

    def update_medicament(self, id, name, producer, price, prescription):
        """
        Modifica un medicament
        Adauga un medicament
        :param id: int, id
        :param name: str, nume
        :param producer: str, producer
        :param price: int, pret
        :param prescription: str, prescription
        :return:
        :return:
        """
        for med in self.get_all():
            if id == med.get_id():
                med_undo = Medicament(med.get_id(), med.get_name(), med.get_producer(), med.get_price(),
                                      med.get_prescription())
                break
        medicament = Medicament(id, name, producer, price, prescription)
        self.__repository.update(medicament)
        '''self.__undo_operations.append(lambda: self.__repository.update(med_undo))
        self.__redo_operations.append(lambda: self.__repository.update(medicament))'''

    def remove_medicament(self, id):
        """
        Sterge un medicament dupa id
        :param id: int, id
        :return:
        """
        listaa = self.__repository.read()
        for medicament in listaa:
            if medicament.get_id() == id:
                self.__repository.delete(id)
                return

    def get_all(self):
        """
        Preia toate entitatile medicament
        :return:
        """
        return self.__repository.read()

    def get_medicament(self, id):
        """
        Preia un medicament dupa un anumit id
        :param id: int, id-ul unui medicament
        :return: class, un medicament
        """
        return self.__repository.read(id)

    def cautare_by_name(self, NAME):
        """
        Cauta un medicament dupa nume
        :param NAME: str, nume
        :return:
        """
        listaa = self.__repository.read()
        lista = filter(lambda med: med.get_name() == NAME, listaa)
        return lista

    def cautare_by_producer(self, PRODUCER):
        """
        Cauta un medicament dupa producer
        :param PRODUCER:
        :return:
        """
        listaa = self.__repository.read()
        lista = []
        for medicament in listaa:
            if medicament.get_producer() == PRODUCER:
                lista.append(medicament)
        return lista

    def cautare_by_price(self, PRICE):
        """
        Cauta un medicament dupa pret
        :param PRICE: int , pret
        :return:
        """
        listaa = self.__repository.read()
        lista = []
        for medicament in listaa:
            if medicament.get_price() == PRICE:
                lista.append(medicament)
        return lista

    def cautare_by_prescription(self, PRESCRIPTION):
        """
        Cauta un medicament dupa prescriptie
        :param PRESCRIPTION: str, prescriptie
        :return:
        """
        listaa = self.__repository.read()
        lista = []
        for medicament in listaa:
            if medicament.get_prescription() == PRESCRIPTION:
                lista.append(medicament)
        return lista

    def scumpire(self, procentaj, valoare):
        """
        Aplica o scumpire cu un anumit procentaj medicamentelor
        :param procentaj: int ,procentaj
        :param valoare: int , valoare
        :return:
        """
        listaa = self.__repository.read()
        for medicament in listaa:
            pret = medicament.get_price()
            if pret < valoare:
                pret = pret + procentaj / 100 * pret
                medicament.set_price(pret)
        return listaa

    def populate(self):
        """
        Populeaza un medicament
        :return:
        """
        letters = string.ascii_lowercase
        id = random.randint(1, 100)
        name = ''.join(random.choice(letters) for i in range(10))
        producer = ''.join(random.choice(letters) for i in range(10))
        price = random.randint(1, 200)
        prescription = random.choice(['Da', 'Nu'])
        self.add_medicament(id, name, producer, price, prescription)

'''    def my_sort_desc(self, lista, key = lambda x: x, reverse = False):
        if len(lista) == 0:
            return []
        pivot = key(lista[0])
        equal_to_pivot = [nr for nr in lista if key(nr) == pivot]
        less_than_pivot = [nr for nr in lista if key(nr) < pivot]
        greater_than_pivot = [nr for nr in lista if key(nr) > pivot]

        return self.my_sort_desc(greater_than_pivot, key) + equal_to_pivot + self.my_sort_desc(less_than_pivot, key)


        return list(
        filter(lambda transaction: lessSum < transaction.getSumMan() + transaction.getSumPieces() < greaterSum,
               self.__repository.read()))'''


'''def test_medicament_service():
    """
    Testeaza repository Tranzactie
    :return:
    """
    repo_medicament = Repository_medicament
    r = Service_Medicament(repo_medicament)
    v1 = Medicament(1, 5, 4, 65, '52/58/58-12:12')
    v2 = Medicament(2, 3, 44, 635, '52/58/58-12:12')
    r.add_medicament(1, 5, 4, 65, '52/58/58-12:12')
    r.add_medicament(2, 3, 44, 635, '52/58/58-12:12')
    assert len(r.get_all()) == 2
    r.update_medicament(2, 3, 44, 635, '52/58/58-12:12')
    r.remove_medicament(1)
    assert len(r.get_all()) == 1
    assert r.get_all() is None
# test_medicament_service()'''
