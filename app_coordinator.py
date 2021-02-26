from Domain.cerc import Cerc
from Repository.Cerc_repository import RepoCerc
from Service.cerc_service import CercService
from User_Interface.console import Console

cerc_repository = RepoCerc()
cerc_service = CercService(cerc_repository)

console = Console(cerc_service)
console.run_console()