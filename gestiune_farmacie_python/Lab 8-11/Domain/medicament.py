from Domain.entity import *


class Medicament(Entity):

    def __init__(self, id, name, producer, price, prescription):
        """
        Crearea entitatii
        :param id: int, id-ul medicamentului
        :param name: str, denumirea medicamentului
        :param producer: str, denumirea producatorului
        :param price: int, pretul medicamentului
        :param prescription: str, Da = necesita prescriptie medicala, Nu = nu necesita prescriptie medicala
        """
        super().__init__(id)
        self.__name = name
        self.__producer = producer
        self.__price = price
        self.__prescription = prescription

    def get_name(self):
        """
        Returneaza denumirea medicamentului
        :return: str, denumirea medicamentului
        """
        return self.__name

    def get_producer(self):
        """
        Returneaza denumirea producatorului
        :return: str, denumirea producatorului
        """
        return self.__producer

    def get_price(self):
        """
        Returneaza pretul medicamentului
        :return: int, pretul medicamentului
        """
        return self.__price

    def get_prescription(self):
        """
        Returneaza prescriptia medicamentului
        :return: str, Da = necesita prescriptie medicala, Nu = nu necesita prescriptie medicala
        """
        return self.__prescription

    def set_name(self, new_name):
        """
        Seteaza o noua denumire medicamentului
        :param new_name: str, noua denumire a medicamentului
        :return: -
        """
        self.__name = new_name

    def set_producer(self, new_producer):
        """
        Seteaza un nou producator
        :param new_producer: str, un nou producator
        :return: -
        """
        self.__producer = new_producer

    def set_price(self, new_price):
        """
        Seteaza un nou pret
        :param new_price: int, noul pret
        :return: -
        """
        self.__price = new_price

    def set_prescription(self, new_prescription):
        """
        Seteaza un noua prescriptie
        :param new_prescription: str, noua prescriptie
        :return: -
        """
        self.__prescription = new_prescription

    def __str__(self):
        """
        Returneaza un string intr-un nou format
        :return: str, o propozitie
        """
        return 'Medicamentul {} cu ID-ul {}, avand pretul de {} RON, produs de {}'.format(self.__name,
                                                                                          self.get_id(),
                                                                                          self.__price,
                                                                                          self.__producer)


def test_medicament_class():
    """
    Testeaza clasa Medicament
    :return:
    """
    medicament = Medicament(1, 'Aspirina', 'IDK', 23, 'Da')
    assert medicament.get_id() == 1
    assert medicament.get_name() == 'Aspirina'
    medicament1 = Medicament(1, 'Aspirina', 'IDK', 23, 'Da')
    # medicament1.set_id(2)
    assert medicament.get_id() != medicament1.get_id()


# test_medicament_class()
