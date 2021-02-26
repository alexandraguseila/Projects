class Cerc:
    def __init__(self, id, x, y, r):
        '''
        Creeaza un cerc
        :param id: int, id-ul cercului
        :param x: float,  abscisa centrului cercului
        :param y: float, ordonata centrului cercului
        :param r: float, raza
        '''
        self.__id = id
        self.__x = x
        self.__y = y
        self.__r = r

    def get_id(self):
        '''
        ia id-ul unui cerc
        :return: id-ul cercului
        '''
        return int(self.__id)

    def get_x(self):
        return float(self.__x)

    def get_y(self):
        return float(self.__y)

    def get_r(self):
        return float(self.__r)

    def __str__(self):
        return 'Cercul {} cu centrul O({},{}) si raza {}'.format(self.__id, self.__x, self.__y, self.__r)

#o=Cerc(1, 2,2,5.6)
#print(o)
#print(o.get_id())
