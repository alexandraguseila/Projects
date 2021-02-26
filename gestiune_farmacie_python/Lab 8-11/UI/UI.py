from Domain.UNDO import Operations


class UI:
    def __init__(self, service_medicament, service_card, service_tranzactie):
        self.__service_medicament = service_medicament
        self.__service_card = service_card
        self.__service_tranzactie = service_tranzactie
        self.__operations_undo = []
        self.__operations_redo = []

    def __show_menu(self):
        print('')
        print('                                                                             MENU')
        print('                                                                      * * * * * * * * * *')
        print('                                                                      *                 *')
        print('                                                                      *  1.MEDICAMENTE  *')
        print('                                                                      *                 *')
        print('                                                                      *  2.CARDURI      *')
        print('                                                                      *                 *')
        print('                                                                      *  3.TRANZACTII   *')
        print('                                                                      *                 *')
        print('                                                                      *  4.EXIT         *')
        print('                                                                      *                 *')
        print('                                                                      * * * * * * * * * *')
        print('')

    def run_console(self):
        while True:
            self.__show_menu()
            option = int(input('OPTION: '))
            if option == 1:
                self.__show_medicamente()
            elif option == 2:
                self.__show_card()
            elif option == 3:
                self.__show_tranzactii()
            elif option == 4:
                break
            else:
                print('***OPTIUNE INVALIDA***   ')

    # _________________________________________________________________________MEDICAMENTE__________________________________

    def __show_medicamente(self):
        self.__show_menu_medicamente()
        while True:
            option1 = int(input('        OPTION: '))
            if option1 == 1:
                print(self.__handle_medicamente_add())
            elif option1 == 2:
                self.__show_list(self.__service_medicament.get_all())
            elif option1 == 3:
                print(self.__delete_medicamente())
            elif option1 == 4:
                break
            elif option1 == 5:
                print(self.__update_medicamente())
            elif option1 == 6:
                self.__show__cautare_medicamente()
                self.__cautare()
            elif option1 == 7:
                print(self.__scumpire())
            elif option1 == 8:
                nr = int(input('        POPULARE DE: '))
                self.__populate_medicamente(nr)
            elif option1 == 9:
                self.undo()
            elif option1 == 10:
                self.redo()
            elif option1 == 11:
                self.search_binary_medicament()
            else:
                print('        OPTIUNE INVALIDA')

    def __show_menu_medicamente(self):
        print('                                   MEDICAMENTE')
        print('                         * * * * * * * * * * * * * * * *')
        print('                         *                             *')
        print('                         *     1.Adaugare              *')
        print('                         *     2.Afisare               *')
        print('                         *     3.Stergere              *')
        print('                         *     4.Back                  *')
        print('                         *     5.Modificare            *')
        print('                         *     6.Cautare medicamente   *')
        print('                         *     7.Scumpire              *')
        print('                         *     8.Populare              *')
        print('                         *     9.Undo                  *')
        print('                         *     10.Redo                 *')
        print('                         *     11.Cautare binara       *')
        print('                         *                             *')
        print('                         * * * * * * * * * * * * * * * *')

    def __handle_medicamente_add(self):
        try:
            id = int(input('        ID: '))
            name = str(input('        NAME: '))
            producer = str(input('        PRODUCER: '))
            price = int(input('        PRICE: '))
            prescription = str(input('        PRESCRIPTION ( Da/Nu ): '))

            add = Operations(lambda: self.__service_medicament.add_medicament(id, name, producer, price, prescription),
                             lambda: self.__service_medicament.remove_medicament(id))
            add.apply_action()
            self.__operations_undo.append(add)
            self.__operations_redo = []
            return '        ***** Medicamentul {} a fost ADAUGAT !!! ***** '.format(id)

        except ValueError as ve:
            print('        ERORI: ')
            for error in ve.args[0]:
                print(error)

    def __delete_medicamente(self):
        ID = int(input('        ID MEDICAMENT: '))
        medicament = self.__service_medicament.get_medicament(ID)

        remove = Operations(lambda: self.__service_medicament.remove_medicament(ID),
                            lambda: self.__service_medicament.add_medicament(medicament.get_id(), medicament.get_name(),
                                                                             medicament.get_producer(),
                                                                             medicament.get_price(),
                                                                             medicament.get_prescription()))
        remove.apply_action()
        self.__operations_undo.append(remove)
        self.__operations_redo = []

        for tranzactie in self.__service_tranzactie.get_all():
            if tranzactie.get_id_medicament() == ID:
                self.__service_tranzactie.remove_tranzactie(tranzactie.get_id())
                """remove1 = Operations(lambda: self.__service_tranzactie.remove_tranzactie(ID),
                                    lambda: self.__service_tranzactie.add_tranzactie(tranzactie.get_id(),
                                                                                     tranzactie.get_id_medicament(),
                                                                                     tranzactie.get_id_card_t(),
                                                                                     tranzactie.get_nr_bucati(),
                                                                                     tranzactie.get_data_ora()))
                remove1.apply_action()
                self.__operations_undo.append(remove1)
                self.__operations_redo = []"""

            # self.__service_tranzactie.remove_tranzactie(tranzactie.get_id_tranzactie())
        return '        ***** Medicamentul {} a fost STERS !!! ***** '.format(ID)

    def __update_medicamente(self):
        ID = int(input('        ID: '))
        new_name = str(input('        NEW NAME: '))
        new_producer = str(input('        NEW PRODUCER: '))
        new_price = int(input('        NEW PRICE: '))
        new_prescription = str(input('        NEW PRESCRIPTION: '))
        self.__service_medicament.update_medicament(ID, new_name, new_producer, new_price, new_prescription)
        return '        ***** Medicamentul cu ID-ul {} a fost MODIFICAT ***** '.format(ID)

    def __show_list(self, objects):
        for object in objects:
            print('       ', object)

    def __show__cautare_medicamente(self):
        print('                                   CAUTARE')
        print('                         * * * * * * * * * * * * * *')
        print('                         *                         *')
        print('                         *     1.BY NAME           *')
        print('                         *     2.BY PRODUCER       *')
        print('                         *     3.BY PRICE          *')
        print('                         *     4.BY PRESCRIPTION   *')
        print('                         *                         *')
        print('                         * * * * * * * * * * * * * *')

    def __cautare(self):
        option = int(input('        SEARCH BY: '))
        if option == 1:
            NAME = str(input('        NAME SEARCH: '))
            self.__show_list(self.__service_medicament.cautare_by_name(NAME))
        elif option == 2:
            PRODUCER = str(input('        PRODUCER SEARCH: '))
            self.__show_list(self.__service_medicament.cautare_by_producer(PRODUCER))
        elif option == 3:
            PRICE = int(input('        PRICE SEARCH: '))
            self.__show_list(self.__service_medicament.cautare_by_price(PRICE))
        elif option == 4:
            PRESCRIPTION = str(input('        PRESRIPTION SEARCH: '))
            self.__show_list(self.__service_medicament.cautare_by_prescription(PRESCRIPTION))

    def __scumpire(self):
        valoare = int(input('        VALOARE INTERVAL: '))
        procent = int(input('        PROCENT SCUMPIRE: '))
        self.__show_list(self.__service_medicament.scumpire(procent, valoare))

    def __populate_medicamente(self, nr):
        i = 0
        while i < nr:
            try:
                self.__service_medicament.populate()
                i += 1
            except Exception as e:
                pass

    def search_binary_medicament(self):
        id = int(input("        ID:"))
        lista = []
        sorted_by_id = list(sorted(self.__service_medicament.get_all(), key=lambda x: x.get_id(), reverse=False))
        for el in sorted_by_id:
            lista.append(el.get_id())
        if self.binary_search(id, lista) is False:
            print('        NU EXISTA')
        else:
            for el in sorted_by_id:
                if el.get_id() == id:
                    print("        EXISTA - ", el)
                    return

    # ______________________________________________________________________________CARD____________________________________

    def __show_card(self):
        self.__show_menu_card()
        while True:
            option1 = int(input('        OPTION: '))
            if option1 == 1:
                print(self.__handle_card_add())
            elif option1 == 2:
                self.__show_list_card(self.__service_card.get_all())
            elif option1 == 3:
                print(self.__delete_card())
            elif option1 == 4:
                break
            elif option1 == 5:
                print(self.__update_card())
            elif option1 == 6:
                self.__show__cautare_card()
                self.__cautare_card()
            elif option1 == 7:
                nr = int(input('        POPULARE DE: '))
                self.__populate_carduri(nr)
            elif option1 == 8:
                self.undo()
            elif option1 == 9:
                self.__permutare()
            elif option1 == 10:
                self.redo()
            elif option1 == 11:
                self.sortare_proprie()
            elif option1 == 12:
                self.search_binary_card()
            else:
                print('        OPTIUNE INVALIDA')

    def __show_menu_card(self):
        print('                                   CARDURI')
        print('                         * * * * * * * * * * * * * *')
        print('                         *                         *')
        print('                         *     1.Adaugare          *')
        print('                         *     2.Afisare           *')
        print('                         *     3.Stergere          *')
        print('                         *     4.Back              *')
        print('                         *     5.Modificare        *')
        print('                         *     6.Cautare carduri   *')
        print('                         *     7.Populare          *')
        print('                         *     8.Undo              *')
        print('                         *     9.Permutare         *')
        print('                         *     10.Redo             *')
        print('                         *     11.Sortare proprie  *')
        print('                         *     12.Cautare binara   *')
        print('                         *                         *')
        print('                         * * * * * * * * * * * * * *')

    def sortare_proprie(self):
        list = self.__service_card.get_all()
        lista = []
        lista1 = []
        for card in list:
            lista.append([card.get_id(), card.get_prenume_person(), card.get_name_person(), card.get_CNP(),
                          card.get_data_inregistrarii()])
            lista1.append(card.get_id())
        self.__service_card.my_sorted_quicksort(lista1, key=lambda x: -x)
        print(lista1)

    def __permutare(self):
        list = self.__service_card.add_permutare()
        for o in list:
            print(o)

    def __populate_carduri(self, nr):
        i = 0
        while i < nr:
            try:
                self.__service_card.populate()
                i += 1
            except Exception as e:
                pass

    def __show__cautare_card(self):
        print('                  CAUTARE')
        print('        * * * * * * * * * * * * * *')
        print('        *                         *')
        print('        *     1.BY NAME           *')
        print('        *     2.BY PRENUME        *')
        print('        *     3.BY CNP            *')
        print('        *     5.BY DATE BIRTH     *')
        print('        *     5.BY DATE INREG.    *')
        print('        *                         *')
        print('        * * * * * * * * * * * * * *')

    def __cautare_card(self):
        option = int(input('        SEARCH BY: '))
        if option == 1:
            NAME = str(input('        NAME SEARCH: '))
            self.__show_list(self.__service_card.cautare_by_name(NAME))
        elif option == 2:
            PRENUME = str(input('        PRENUME SEARCH: '))
            self.__show_list(self.__service_card.cautare_by_prenume(PRENUME))
        elif option == 3:
            CNP = str(input('        CNP SEARCH: '))
            self.__show_list(self.__service_card.cautare_by_CNP(CNP))
        elif option == 4:
            DATA_NASTERII = str(input('        DATE BIRTH SEARCH: '))
            self.__show_list(self.__service_card.cautare_by_data_nasterii(DATA_NASTERII))
        elif option == 5:
            DATA_INREGISTRARII = str(input('        DATE INREG SEARCH: '))
            self.__show_list(self.__service_card.cautare_by_data_inregistrarii(DATA_INREGISTRARII))

    def __handle_card_add(self):
        try:
            id_card = int(input('        ID: '))
            name_person = str(input('        NAME PERSON: '))
            prenume_person = str(input('        PRENUME PERSON: '))
            CNP = str(input('        CNP: '))
            data_nasterii = str(input('        DATA NASTERII: '))
            data_inregistrarii = str(input('        DATA INREGISTRARII: '))

            add = Operations(lambda: self.__service_card.add_card(id_card, name_person, prenume_person, CNP,
                                                                  data_nasterii, data_inregistrarii),
                             lambda: self.__service_card.remove_card(id_card))
            add.apply_action()
            self.__operations_undo.append(add)
            self.__operations_redo = []

            return '         ***** Cardul {} a fost ADAUGAT !!! ***** '.format(id_card)
        except ValueError as ve:
            print('        ERORI: ')
            for error in ve.args[0]:
                print(error)

    def __update_card(self):
        ID_CARD = int(input('        ID_CARD: '))
        new_name_person = str(input('        NEW NAME: '))
        new_prenume_person = str(input('        NEW PRENUME: '))
        new_CNP = str(input('        NEW CNP: '))
        new_data_nasterii = str(input('        NEW DATA NASTERII: '))
        new_data_inregistrarii = str(input('        NEW DATA INREGISTRARII: '))
        self.__service_card.update_card(ID_CARD, new_name_person, new_prenume_person, new_CNP, new_data_nasterii,
                                        new_data_inregistrarii)
        return '         ***** Cardul {} a fost MODIFICAT !!! ***** '.format(ID_CARD)

    def __delete_card(self):
        ID = int(input('        ID Card: '))
        card = self.__service_card.get_card(ID)

        remove = Operations(lambda: self.__service_card.remove_card(ID),
                            lambda: self.__service_card.add_card(card.get_id(), card.get_name_person(),
                                                                 card.get_prenume_person(),
                                                                 card.get_CNP(),
                                                                 card.get_data_nasterii(),
                                                                 card.get_data_inregistrarii()))
        remove.apply_action()
        self.__operations_undo.append(remove)
        self.__operations_redo = []

        for tranzactie in self.__service_tranzactie.get_all():
            if tranzactie.get_id_card_t() == ID:
                self.__service_tranzactie.remove_tranzactie(tranzactie.get_id_tranzactie())
        return '         ***** Cardul {} a fost STERS !!! ***** '.format(ID)

    def __show_list_card(self, objects):
        for object in objects:
            print('       ', object)

    def search_binary_card(self):
        id = int(input("        ID:"))
        lista = []
        sorted_by_id = list(sorted(self.__service_card.get_all(), key=lambda x: x.get_id(), reverse=False))
        for el in sorted_by_id:
            lista.append(el.get_id())
        if self.binary_search(id, lista) is False:
            print('        NU EXISTA')
        else:
            for el in sorted_by_id:
                if el.get_id() == id:
                    print("        EXISTA - ", el)
                    return

    # ________________________undo la ma mult de 1______________TRANZACTII______________________________________________

    def __show_tranzactii(self):
        self.__show_menu_tranzactii()
        while True:
            option1 = int(input('        OPTION: '))
            if option1 == 1:
                print(self.__handle_tranzactii_add())
            elif option1 == 2:
                self.__show_list_tranzactii(self.__service_tranzactie.get_all())
            elif option1 == 3:
                print(self.__delete_tranzactii())
            elif option1 == 4:
                break
            elif option1 == 5:
                print(self.__update_tranzactie())
            elif option1 == 6:
                self.__show_list_tranzactii(self.__sortare_by_nr())
                print('        ***** Tranzactiile au fost SORTATE ***** ')
            elif option1 == 7:
                print(self.__interval_tranzactii())
            elif option1 == 8:
                self.__interval_stergere()
            elif option1 == 9:
                nr = int(input('        POPULARE DE: '))
                self.__populate_tranzactii(nr)
            elif option1 == 10:
                self.undo()
            elif option1 == 11:
                self.redo()
            elif option1 == 12:
                self.search_binary_tranzactie()
            else:
                print('        OPTIUNE INVALIDA')

    def __show_menu_tranzactii(self):
        print('                                  TRANZACTII')
        print('                         * * * * * * * * * * * * * *')
        print('                         *                         *')
        print('                         *      1.Adaugare         *')
        print('                         *      2.Afisare          *')
        print('                         *      3.Stergere         *')
        print('                         *      5.Modificare       *')
        print('                         *      4.Back             *')
        print('                         *      6.Sortare med/nr   *')
        print('                         *      7.Tranz. interval  *')
        print('                         *      8.Sterg. interval  *')
        print('                         *      9.Populare         *')
        print('                         *      10.Undo            *')
        print('                         *      11.Redo            *')
        print('                         *      12.Cautare binara  *')
        print('                         *                         *')
        print('                         * * * * * * * * * * * * * *')

    def __populate_tranzactii(self, nr):
        i = 0
        while i < nr:
            try:
                self.__service_tranzactie.populate()
                i += 1
            except Exception as e:
                pass

    def __sortare_by_nr(self):
        return self.__service_tranzactie.sorted_med_by_nr_bucati()

    def __interval_tranzactii(self):
        d1 = int(input('        ZIUA 1: '))
        d2 = int(input('        ZIUA 2: '))
        return self.__service_tranzactie.interval(d1, d2)

    def __interval_stergere(self):
        d1 = int(input('        ZIUA 1: '))
        d2 = int(input('        ZIUA 2: '))
        self.__service_tranzactie.stergere_int(d1, d2)

    def __handle_tranzactii_add(self):
        try:
            id_tranzactie = int(input('        ID TRANZACTIE: '))
            id_medicament = int(input('        ID MEDICAMENT: '))
            id_card_t = int(input('        ID CARD: '))
            nr_bucati = int(input('        NR BUCATI: '))
            data_ora = str(input('        DATA & ORA: '))

            add = Operations(lambda: self.__service_tranzactie.add_tranzactie(id_tranzactie, id_medicament, id_card_t,
                                                                              nr_bucati, data_ora),
                             lambda: self.__service_tranzactie.remove_tranzactie(id_tranzactie))
            add.apply_action()
            self.__operations_undo.append(add)
            self.__operations_redo = []

            return '        ***** Tranzactia {} a fost ADAUGATA ***** '.format(id_tranzactie)
        except ValueError as ve:
            print('        ERORI: ')
            for error in ve.args[0]:
                print(error)

    def __update_tranzactie(self):
        ID = int(input('        ID: '))
        new_id_medicament = int(input('        NEW ID MEDICAMENT: '))
        new_id_card_t = int(input('        NEW ID CARD: '))
        new_nr_bucati = int(input('        NEW NR BUCATI: '))
        new_data_ora = str(input('        NEW DATA & ORA: '))
        self.__service_tranzactie.update_tranzactie(ID, new_id_medicament, new_id_card_t, new_nr_bucati, new_data_ora)
        return '        ***** Tranzactia {} a fost MODIFICATA ***** '.format(ID)

    def __delete_tranzactii(self):
        ID = int(input('        ID: '))
        tranzactie = self.__service_tranzactie.get_tranzactie(ID)

        remove = Operations(lambda: self.__service_tranzactie.remove_tranzactie(ID),
                            lambda: self.__service_tranzactie.add_tranzactie(tranzactie.get_id(),
                                                                             tranzactie.get_id_medicament(),
                                                                             tranzactie.get_id_card_t(),
                                                                             tranzactie.get_nr_bucati(),
                                                                             tranzactie.get_data_ora()))
        remove.apply_action()
        self.__operations_undo.append(remove)
        self.__operations_redo = []

        return '        ***** Tranzactia {} a fost STEARSA ***** '.format(ID)

    def __show_list_tranzactii(self, objects):
        '''if len(objects) >= 1:
            print(objects[0])
            return self.__show_list_tranzactii(objects[1:])
        else:
            return 0'''
        for object in objects:
            print('       ', object)
        # self.__show_tranzactii(objects)
        # for object in objects:
        #   print(object)

    def search_binary_tranzactie(self):
        id = int(input("        ID:"))
        lista = []
        sorted_by_id = list(sorted(self.__service_tranzactie.get_all(), key=lambda x: x.get_id(), reverse=False))
        for el in sorted_by_id:
            lista.append(el.get_id())
        if self.binary_search(id, lista) is False:
            print('        NU EXISTA')
        else:
            for el in sorted_by_id:
                if el.get_id() == id:
                    print("        EXISTA - ", el)
                    return

    def undo(self):
        try:
            if len(self.__operations_undo) > 0:
                undo = self.__operations_undo.pop()
                undo.apply_reverse_action()
                self.__operations_redo.append(undo)
            else:
                raise ValueError('        Nu se poate face Undo')
        except ValueError as e:
            print(e)

    def redo(self):
        try:
            if len(self.__operations_redo) > 0:
                redo = self.__operations_redo.pop()
                redo.apply_action()
                self.__operations_undo.append(redo)
            else:
                raise ValueError("        Nu se poate face redo")
        except ValueError as e:
            print(e)

    def binary_search(self, nr, lista):
        st = 0
        dr = len(lista) - 1
        while st <= dr:
            m = (st + dr) // 2
            if lista[m] == nr:
                return True
            if lista[m] < nr:
                st = m + 1
            else:
                dr = m - 1
        return False
