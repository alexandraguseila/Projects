from Domain.cerc import Cerc
import json

class RepoCerc:


    def __init__(self):
        self.__dict = self.read_from_file()

    def get_cerc(self, id_cerc=None):
        '''
        Returneaza toate cercurile din memorie sau cercul cu id-ul dat daca acesta exista
        :param id_cerc:
        :return:
        '''
        if id_cerc is None:
            return self.__dict.values()
        if id_cerc in self.__dict:
            return self.__dict[id_cerc]
        return None


    def save_to_file(self, cercuri):
        '''
        Salveaza cercurile din memorie in fisierokl,
        :param cercuri:
        :return:
        '''
        with open('CERC.txt', "w") as f:
            content = " "
            for cerc in cercuri.values():
                line = "ID:{}, Centru:(x:{},y:{}), Raza:{}\n".format(cerc.get_id(),
                                                cerc.get_x(),
                                                cerc.get_y(),
                                                cerc.get_r(),)
                content += line
            f.write(content)


    def read_from_file(self):
        '''
        Reads a list of cercuri from a file containing json
        :return: a list of cercuri
        '''
        try:
            with open('CERC.txt', 'r') as f_in:
                string = f_in.read()
                components = string.split('\n')
                components.pop()
                dict = {}
                for cerc in components:
                    propr_cerc = cerc.split(',')  # primul cerc
                    id_string = propr_cerc[0].split(':')
                    id = int(id_string[1])
                    raza_string = propr_cerc[3].split(':')
                    raza = float(raza_string[1])
                    x_centru_string = propr_cerc[1].split(':')
                    x_centru = float(x_centru_string[2])
                    y_centru_string = propr_cerc[2].split(':')
                    y_centru = float(y_centru_string[1][:-1])
                    cerculet = Cerc(id, x_centru, y_centru, raza)
                    dict[id] = cerculet
                return dict
        except FileNotFoundError:
            return []


    def create_cerc(self, cerc):
        '''
        Creeaza un cerc in memorie
        '''
        if cerc.get_id() in self.__dict:
            raise KeyError("id deja exsitent")
        self.__dict[cerc.get_id()] = cerc
        self.save_to_file(self.__dict)

    def delete_cerc(self, id_de_sters):
        '''
        sterge cercul cu id ul dat
        :param id_de_sters:
        :return:
        '''
        if id_de_sters in self.__dict:
            del(self.__dict[id_de_sters])
            self.save_to_file(self.__dict)





