from Domain.card import Card

CARDURI = 'CARDURI'


class Repository_card:

    def __init__(self):
        """
        Creeaza entitatile
        """
        self.__file = CARDURI
        self.__items = []
        self.__items = self.__read_file()

    def __read_file(self):
        """
        Citeste din fisier
        :return: list, lista
        """
        lista = []
        f = open(self.__file, "r")
        lines = f.readlines()
        for line in lines:
            card_string = line[:-1]
            components = card_string.split("/")
            id_card = int(components[0])
            name_person = components[1]
            prenume_person = components[2]
            CNP = components[3]
            data_nasterii = components[4]
            data_inregistrarii = components[5]
            card = Card(id_card, name_person, prenume_person, CNP, data_nasterii, data_inregistrarii)
            lista.append(card)
        f.close()
        return lista

    def __write_file(self, list_carduri):
        """
        Scrie in fisier
        :param list_carduri: list, lista de carduri
        :return: -
        """
        f = open(self.__file, "w")
        content = " "
        for card in list_carduri:
            line = "{}/{}/" \
                   "{}/{}/{}/{}\n".format(card.get_id_card(),
                                          card.get_name_person(),
                                          card.get_prenume_person(),
                                          card.get_CNP(),
                                          card.get_data_nasterii(),
                                          card.get_data_inregistrarii())
            content += line
        f.write(content)
        f.close()

    def create_card(self, card):
        """
        Creaza un card
        :param card: class, card
        :return: -
        """
        self.__read_file()
        for c in self.__items:
            if c.get_id_card() == card.get_id_card():
                raise ValueError('-> ID Exist <-')
        for c in self.__items:
            if c.get_CNP() == card.get_CNP():
                raise ValueError('-> CNP Exist <-')
        self.__items.append(card)
        self.__write_file(self.__items)

    def read_card(self):
        """
        Preia toate cardurile
        :return: list, toate cardurile
        """
        self.__read_file()
        return self.__items[:]

    def update_card(self, card):
        """
        Modifica un card
        :param card: class, card
        :return: -
        """
        self.__read_file()
        for i in range(len(self.__items)):
            if self.__items[i].get_id_card() == card.get_id_card():
                self.__items[i] = card
                return
        self.__write_file(self.__items)

    def delete_card(self, card):
        """
        Sterge un card
        :param card: class, card
        :return: -
        """
        self.__read_file()
        self.__items.remove(card)
        self.__write_file(self.__items)


def test_card_repository():
    """
    Testeaza repository card
    :return:
    """
    r = Repository_card()
    lungime = len(r.read_card())
    v1 = Card(1, 'Hoban', 'Paul', '225224225', '52/58/58', '58/58/68/')
    v2 = Card(2, 'Hoban', 'Pavel', '225224225', '52/58/58', '58/58/68/')
    r.create_card(v1)
    r.create_card(v2)
    assert len(r.read_card()) == lungime + 2
    r.update_card(Card(1, 'Hoban', 'Adriana', '225224225', '52/58/58', '58/58/68/'))
    r.delete_card(1)
    assert len(r.read_card()) == lungime + 1
    # assert r.read_card() == None

# test_card_repository()
