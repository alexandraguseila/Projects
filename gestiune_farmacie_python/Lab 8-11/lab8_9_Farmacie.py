from Repository.repository_generic import *
from Service.service_tranzactie import Service_Tranzactie
from Service.service_card import Service_Card
from Service.service_medicament import Service_Medicament
from UI.UI import UI


repo_medicament = Repository_generic('MEDICAMENT')
repo_tranzactie = Repository_generic('TRANZACTIE')
repo_card = Repository_generic('CARDURI')


serv_medicament = Service_Medicament(repo_medicament)
serv_tranzactie = Service_Tranzactie(repo_tranzactie, repo_medicament, repo_card)
serv_card = Service_Card(repo_card)


console = UI(serv_medicament, serv_card, serv_tranzactie)
console.run_console()
