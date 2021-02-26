from Domain.entity import *


class Card(Entity):

    def __init__(self, id_card, name_person, prenume_person, CNP, data_nasterii, data_inregistrarii):
        """
        Crearea entitatii
        :param id_card: int, id-ul cardului
        :param name_person: str, numele persoanei
        :param prenume_person: str, prenumele persoanei
        :param CNP: str, CNP-ul persoanei
        :param data_nasterii: str, data nasterii persoanei
        :param data_inregistrarii: str, data inregistrarii persoanei
        """
        super().__init__(id_card)
        self.__name_person = name_person
        self.__prenume_person = prenume_person
        self.__CNP = CNP
        self.__data_nasterii = data_nasterii
        self.__data_inregistrarii = data_inregistrarii

    def get_name_person(self):
        """
        Returneaza numele persoanei
        :return: str, numele persoanei
        """
        return self.__name_person

    def get_prenume_person(self):
        """
        Returneaza prenumele persoanei
        :return: str, prenumele persoanei
        """
        return self.__prenume_person

    def get_CNP(self):
        """
        Returneaza CNP ul
        :return: str, CNP-ul persoanei
        """
        return self.__CNP

    def get_data_nasterii(self):
        """
        Returneaza data nasterii
        :return: str, data nasterii persoanei
        """
        return self.__data_nasterii

    def get_data_inregistrarii(self):
        """
        Returneaza data inregistrarii
        :return: str, data inregistrarii persoanei
        """
        return self.__data_inregistrarii

    def set_name_person(self, new_name_person):
        """
        Steaza un nou nume persoanei
        :param new_name_person: str, noul nume al persoanei
        :return: -
        """
        self.__name_person = new_name_person

    def set_prenume_person(self, new_prenume_person):
        """
        Seteaza un nou prenume
        :param new_prenume_person: str, noul prenume al persoanei
        :return: -
        """
        self.__prenume_person = new_prenume_person

    def set_CNP(self, new_CNP):
        """
        Seteaza un nou CNP
        :param new_CNP: noul CNP al persoanei
        :return: -
        """
        self.__CNP = new_CNP

    def set_data_nasterii(self, new_data_nasterii):
        """
        Seteaza o noua data de nastere
        :param new_data_nasterii: str, o noua data de nastere a persoanei
        :return: -
        """
        self.__data_nasterii = new_data_nasterii

    def set_data_inregistrarii(self, new_data_inregistrarii):
        """
        Seteaza o noua data de inregistrare
        :param new_data_inregistrarii: str, o noua data de inregistrare a persoanei
        :return: -
        """
        self.__data_inregistrarii = new_data_inregistrarii

    def __str__(self):
        """
        Returneaza un string intr-un anumit format
        :return: str, o propozitie
        """
        return 'Cardul nr. {} inregistrat pe {}, cu titularul {} {}, avand CNP-ul {}, nascut pe {}'.format(
            self.get_id(),
            self.__data_inregistrarii,
            self.__name_person,
            self.__prenume_person,
            self.__CNP,
            self.__data_nasterii)


def test_card_class():
    """
    Testeaza clasa Card
    :return:
    """
    card = Card(1, 'Hoban', 'Paul-Adelin', '5000928456654', '3234', '3242')
    assert card.get_id() == 1
    assert card.get_CNP() == '5000928456654'
    card1 = Card(1, 'Hoban', 'Paul-Adelin', '5000928456654', '3234', '3242')
    # card1.set_id_card(2)
    assert card.get_id() != card1.get_id()

# test_card_class()
