from Domain.tranzactie import Tranzactie

TRANZACTII = 'TRANZACTII'


class Repository_tranzactie:

    def __init__(self):
        """
        Creeaza entitatile
        """
        self.__file = TRANZACTII
        self.__items = []
        self.__items = self.__read_file()

    def __read_file(self):
        """
        Citeste din fisier
        :return: lista, lista
        """
        lista = []
        f = open(self.__file, "r")
        lines = f.readlines()
        for line in lines:
            tranzactie_string = line[:-1]
            components = tranzactie_string.split("/")
            id_tranzactie = int(components[0])
            id_medicament = int(components[1])
            id_card_t = int(components[2])
            nr_bucati = int(components[3])
            data_ora = components[4]
            tranzactie = Tranzactie(id_tranzactie, id_medicament, id_card_t, nr_bucati, data_ora)
            lista.append(tranzactie)
        f.close()
        return lista

    def __write_file(self, list_tranzactie):
        """
        Scrie in fisier
        :param list_tranzactie: list, lista de tranzactii
        :return: -
        """
        f = open(self.__file, "w")
        content = " "
        for tranzactie in list_tranzactie:
            line = "{}/{}/{}/{}/{}\n".format(tranzactie.get_id_tranzactie(),
                                             tranzactie.get_id_medicament(),
                                             tranzactie.get_id_card_t(),
                                             tranzactie.get_nr_bucati(),
                                             tranzactie.get_data_ora())
            content += line
        f.write(content)
        f.close()

    def create_tranzactie(self, tranzactie):
        """
        Creaza o tranzactie
        :param tranzactie: class, tranzactie
        :return: -
        """
        self.__read_file()
        for t in self.__items:
            if t.get_id_tranzactie() == tranzactie.get_id_tranzactie():
                raise ValueError('-> ID Exist <-')
        self.__items.append(tranzactie)
        self.__write_file(self.__items)

    def read_tranzactie(self):
        """
        Preia taate tranzactiile
        :return: list, toate tranzactiile
        """
        self.__read_file()
        return self.__items[:]

    def update_tranzactie(self, tranzactie):
        """
        Modifica o tranzactie
        :param tranzactie: class, o tranzactie
        :return: -
        """
        self.__read_file()
        for i in range(len(self.__items)):
            if self.__items[i].get_id_tranzactie == tranzactie.get_id_tranzactie():
                self.__items[i] = tranzactie
                return
        self.__write_file(self.__items)

    def delete_tranzactie(self, tranzactie):
        """
        Sterge o tranzactie
        :param tranzactie: class, o tranzactie
        :return: -
        """
        self.__read_file()
        self.__items.remove(tranzactie)
        self.__write_file(self.__items)


def test_tranzactie_repository():
    """
    Testeaza repository Tranzactie
    :return:
    """
    r = Repository_tranzactie()
    lungime = len(r.read_tranzactie())
    v1 = Tranzactie(1, 5, 4, 65, '52/58/58-12:12')
    v2 = Tranzactie(2, 3, 44, 635, '52/58/58-12:12')
    r.create_tranzactie(v1)
    r.create_tranzactie(v2)
    assert len(r.read_tranzactie()) == lungime + 2
    r.update_tranzactie(Tranzactie(1, 34, 4, 65, '52/58/58-12:12'))
    r.delete_tranzactie(1)
    assert len(r.read_tranzactie()) == lungime + 1
    # assert r.read_tranzactie() == None

# test_tranzactie_repository()
