from Domain.medicament import Medicament

MEDICAMENTE = 'MEDICAMENTE'


class Repository_medicament:

    def __init__(self):
        """
        Creeaza entitatile
        """
        self.__file = MEDICAMENTE
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
            medicament_string = line[:-1]
            components = medicament_string.split("/")
            id = int(components[0])
            name = components[1]
            producer = components[2]
            price = float(components[3])
            prescription = False
            if components[4]:
                prescription = True
            medicament = Medicament(id, name, producer, price, prescription)
            lista.append(medicament)
        f.close()
        return lista

    def __write_file(self, list_medicamente):
        """
        Scrie in fisier
        :param list_medicamente: list, lista de medicamente
        :return: -
        """
        f = open(self.__file, "w")
        content = " "
        for medicament in list_medicamente:
            line = "{}/{}/{}/{}/{}\n".format(medicament.get_id(),
                                             medicament.get_name(),
                                             medicament.get_producer(),
                                             medicament.get_price(),
                                             medicament.get_prescription())
            content += line
        f.write(content)
        f.close()

    def create_medicament(self, medicament):
        """
        creaza un medicament
        :param medicament: class, medicament
        :return: -
        """
        self.__read_file()
        for m in self.__items:
            if m.get_id() == medicament.get_id():
                raise ValueError('-> ID Exist <-')
        self.__items.append(medicament)
        self.__write_file(self.__items)

    def read_medicamente(self):
        """
        Preia toate medicamentele
        :return: list, toate medicamentele
        """
        self.__read_file()
        return self.__items[:]

    def update_medicament(self, medicament):
        """
        Modifica un medicament
        :param medicament: class, medicamnet
        :return: -
        """
        self.__read_file()
        for i in range(len(self.__items)):
            if self.__items[i].get_id() == medicament.get_id():
                self.__items[i] = medicament
                return
        self.__write_file(self.__items)

    def delete_medicament(self, medicament):
        """
        Sterge un medicament
        :param medicament: class, medicament
        :return: -
        """
        self.__read_file()
        self.__items.remove(medicament)
        self.__write_file(self.__items)



def test_medicament_repository():
    """
    Testeaza repository medicament
    :return:
    """
    r = Repository_medicament()
    lungime = len(r.read_medicamente())
    v1 = Medicament(1, 'Aspirina', 'Aspa', 23, 'Da')
    v2 = Medicament(2, 'Paracetamol', 'Paraceta', 45, 'Nu')
    r.create_medicament(v1)
    r.create_medicament(v2)
    assert len(r.read_medicamente()) == lungime + 2
    r.update_medicament(Medicament(1, 'Aspirina', 'Aspa', 767, 'Nu'))
    r.delete_medicament(1)
    assert len(r.read_medicamente()) == lungime + 1
    # assert r.read_medicamente() == None

# test_medicament_repository()
