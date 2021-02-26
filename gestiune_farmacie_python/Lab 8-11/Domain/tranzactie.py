from Domain.entity import *


class Tranzactie(Entity):

    def __init__(self, id_tranzactie, id_medicament, id_card_t, nr_bucati, data_ora):
        """
        Crearea entitatii
        :param id_tranzactie: int, id-ul tranzactiei
        :param id_medicament: int, id-ul medicamentului
        :param id_card_t: int, id-ul cardului
        :param nr_bucati: int, numarul de bucati
        :param data_ora: int, data si ora
        """
        super().__init__(id_tranzactie)
        self.__id_medicament = id_medicament
        self.__id_card_t = id_card_t
        self.__nr_bucati = nr_bucati
        self.__data_ora = data_ora

    def get_id_medicament(self):
        """
        Returneaza id-ul medicamentului
        :return: int, id-ul medicamentului
        """
        return self.__id_medicament

    def get_id_card_t(self):
        """
        Returneaza id-ul cardului
        :return: int, id-ul cardului
        """
        return self.__id_card_t

    def get_nr_bucati(self):
        """
        Returneaza numarul de  bucati
        :return: int, numarul de  bucati
        """
        return self.__nr_bucati

    def get_data_ora(self):
        """
        Returneaza data si ora
        :return: str, data si ora
        """
        return self.__data_ora

    def set_id_medicament(self, new_id_medicament):
        """
        Seteaza un nou id medicamentului
        :param new_id_medicament: int, un nou al id medicamentului
        :return: -
        """
        self.__id_medicament = new_id_medicament

    def set_id_card_t(self, new_id_card_t):
        """
        Seteaza un nou id cardului
        :param new_id_card_t: int, noul id al cardului
        :return: -
        """
        self.__id_card_t = new_id_card_t

    def set_nr_bucati(self, new_nr_bucati):
        """
        Seteaza un nou numar de bucati
        :param new_nr_bucati: int, noul numar de bucati
        :return: -
        """
        self.__nr_bucati = new_nr_bucati

    def set_data_ora(self, new_data_ora):
        """
        Seteaza o noua data si ora
        :param new_data_ora: str, o noua data si ora
        :return: -
        """
        self.__data_ora = new_data_ora

    def __str__(self):
        """
        Returneaza un string intr-un anumit format
        :return: str, o propozitie
        """
        return 'Tranzactia {} realizata pe {}, cu medicamentul {}, realizata cu cardul {} contine {} bucati'.format(
            self.get_id(),
            self.__data_ora,
            self.__id_medicament,
            self.__id_card_t,
            self.__nr_bucati)


'''def test_tranzactie_class():
    """
    Testeaza clasa Tranzactie
    :return:
    """
    tranzactie = Tranzactie(1, 23, 45, 23, '23/11/19-12:13')
    assert tranzactie.get_id_tranzactie() == 1
    assert tranzactie.get_data_ora() == '23/11/19-12:13'
    tranzactie1 = Tranzactie(1, 23, 45, 23, '23/11/19-12:13')
    tranzactie1.set_id_tranzactie(2)
    assert tranzactie.get_id_tranzactie() != tranzactie1.get_id_tranzactie()


test_tranzactie_class()'''
