class Console:

    def __init__(self, cerc_service):
        self.__cerc_service = cerc_service

    def __show_cercuri(self, lista_de_cercuri):
        for cerc in lista_de_cercuri:
            print(cerc)

    def __show_menu(self):
        print('1.Adaugare cerc')
        print('2.Stergerea tuturor cercurilor intr-un anumit interval')
        print('3.Sortare descrescator dupa arie')
        print('4.Afisarea tuturor cercurilor care contin un punct dat')
        print('a.Afisare')

    def run_console(self):
        while True:
            self.__show_menu()
            op = input('Ce comanda doriti sa efectuati? ')
            if op == '1':
                id = int(input('Id-ul este '))
                x = float(input('Abscisa centrului cercului este '))
                y = float(input('Ordonata cercului este '))
                r = float(input('Raza cercului este '))
                self.__cerc_service.add_cerc(id, x, y, r)
                print("Cerc adaugat!")
            elif op == '2':
                capat1 = input('Primul capat al intervalului este ')
                capat2 = input('Al doilea capat al intervalului este ')
                self.__cerc_service.remove_cerc(capat1, capat2)
                print('Cercurile au fost sterse')
            elif op == '3':
                self.__show_cercuri(self.__cerc_service.sort_arie())
            elif op == '4':
                x_punct = int(input('Abscisa punctului este '))
                y_punct = int(input('Ordonata punctului este '))
                dict = self.__cerc_service.contine_punctul(x_punct, y_punct)
                for cerc in dict.values():
                    print(cerc)
            elif op == 'f':
                dict = self.__cerc_service.get_from_file()
                for cerc in dict.values():
                    print(cerc)


            elif op == 'a':
                self.__show_cercuri(self.__cerc_service.get_all())
            else:
                break
