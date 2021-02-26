from Domain.cerc import *

class CercService:
    '''
    operatiile care trebuie efectuate asupra cercurilor
    '''

    def __init__(self, repository):
        self.__memorie = repository

    def add_cerc(self, id, x, y, r):
        cerc = Cerc(id, x, y, r)
        self.__memorie.create_cerc(cerc)

    def get_from_file(self):
        return self.__memorie.read_from_file()

    def get_all(self):
        '''
        Preia toate cercurile existente
        :return: toate cercurile din memorie
        '''
        return self.__memorie.get_cerc()

    def remove_cerc(self, capat1_interval, capat2_interval):
        '''
        sterge toate cercurile care au raza intr-un interval dat
        :param id_de_sters:
        :param capat1_interval:
        :param capat2_interval:
        :return:
        '''
        id_de_sters=[]
        for cerc in self.get_all():
            if ( cerc.get_r()>=float(capat1_interval)) and (cerc.get_r()<= float(capat2_interval)):
                id_de_sters.append(cerc.get_id())
        for id in id_de_sters:
            self.__memorie.delete_cerc(id)

    def sort_arie(self):
        return sorted(self.get_all(), key = lambda cerc: 3.14 * cerc.get_r(), reverse=True)

    def contine_punctul(self, x_punct, y_punct):
        dict = {}
        for cerc in self.get_all():
            a = cerc.get_x()
            b = cerc.get_y()
            r = cerc.get_r()
            if (x_punct - a) * (x_punct - a) + (y_punct - b) * (y_punct - b) == r*r:
                dict[cerc.get_id()] = cerc
        return dict


