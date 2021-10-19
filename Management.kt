import java.lang.Exception
import java.util.*

var listHostConfig: MutableList<HostConfig> = mutableListOf()
var repeat = false
val scanner = Scanner(System.`in`)
const val regex = "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)(\\.(?!\$)|\$)){4}\$"

fun main() {

    println("\n======================")
    println("Welcome to Host Config")
    loop@ do {
        println("\n======================\n")
        println("Enter 1 to input a list host config")
        println("Enter 2 to show information of all host config")
        println("Enter 0 to exit")
        print("Your choice: ")

        val n = scanner.next()

        when (n) {
            "0" -> {
                println("You chose exit.")
                break@loop
            }
            "1" -> {
                inputHostConfig()
            }
            "2" -> {
                showHostConfig()
            }
        }
    } while ((n != "0" && n != "1" && n != "2") || repeat)
}

fun showHostConfig() {
    var nShow: String
    var flag = false
    do {
        println("\n======================\n")
        println("Enter 1 to show host config by IP")
        println("Enter 2 to show host config by port")
        println("Enter 3 to show host config by type connection")
        println("Enter 4 to show all host config")
        println("Enter 0 to exit")
        print("Your choice: ")

        nShow = scanner.next()
        when (nShow) {
            "0" -> {
                println("Exit show host config.")
                flag = false
                repeat = true
            }
            "1" -> {
                println("\n=====Show host config by IP=====\n")
                searchByIp()
                flag = true
            }
            "2" -> {
                println("\n=====Show host config by port=====\n")
                searchByPort()
                flag = true
            }
            "3" -> {
                println("\n=====Show host config by type connection=====\n")
                searchByTypeConnection()
                flag = true
            }
            "4" -> {
                println("\n=====Show all host config=====\n")
                showAll()
                flag = true
            }
        }
    } while ((nShow != "0" && nShow != "1" && nShow != "2" && nShow != "3" && nShow != "4") || flag)
}

fun showAll() {
    if (listHostConfig.size == 0) {
        println("No host config found")
    } else {
        println("${listHostConfig.size} host config\n")
        for (i in listHostConfig) {
            println(i)
        }
    }
}

fun searchByTypeConnection() {
    val listTypeConnection = mutableListOf<HostConfig>()
    var typeConnection: String
    do {
        println("Choose type connection: ")
        println("1. WIFI\t2. DATA_4G\t3. DATA_5G\t4. Others")
        print("Your choice: ")
        typeConnection = scanner.next()

        when (typeConnection) {
            "1" -> {
                for (i in listHostConfig) {
                    if (i.typeConnection == "WIFI") {
                        listTypeConnection.add(i)
                    }
                }
            }
            "2" -> {
                for (i in listHostConfig) {
                    if (i.typeConnection == "DATA_4G") {
                        listTypeConnection.add(i)
                    }
                }
            }
            "3" -> {
                for (i in listHostConfig) {
                    if (i.typeConnection == "DATA_5G") {
                        listTypeConnection.add(i)
                    }
                }
            }
            "4" -> {
                for (i in listHostConfig) {
                    if (i.typeConnection == "Others") {
                        println(i)
                    }
                }
            }
        }
    } while (typeConnection != "1" && typeConnection != "2" && typeConnection != "3" && typeConnection != "4")

    if (listTypeConnection.size == 0) {
        println("Not found")
    } else {
        println("${listTypeConnection.size} found")
        for (i in listTypeConnection) {
            println(i)
        }
    }
}

fun searchByPort() {
    print("Enter a port: ")
    var port = 0
    do {
        var f = false
        try {
            print("Enter port: ")
            port = scanner.nextInt()
        } catch (ex: Exception) {
            println("Enter a positive integer")
            f = true
            scanner.next()
        }
    } while (f || port <= 0)

    val listPort = mutableListOf<HostConfig>()
    for (i in listHostConfig) {
        if (i.port == port) {
            listPort.add(i)
        }
    }
    if (listPort.size == 0) {
        println("Not found")
    } else {
        println("${listPort.size} found")
        for (i in listPort) {
            println(i)
        }
    }
}

fun searchByIp() {
    var ip: String
    do {
        print("Enter a version 4 IP: ")
        ip = scanner.next()
    } while (!ip.matches(regex.toRegex()))

    val listIp = mutableListOf<HostConfig>()
    for (i in listHostConfig) {
        if (i.ip == ip) {
            listIp.add(i)
        }
    }
    if (listIp.size == 0) {
        println("Not found")
    } else {
        println("${listIp.size} found")
        for (i in listIp) {
            println(i)
        }
    }
}

fun inputHostConfig() {
    var nInput: String
    var flag = false
    do {
        println("\n======================\n")
        println("Enter 1 to input a host config and generate it")
        println("Enter 0 to return main menu")
        print("Your choice: ")

        nInput = scanner.next()
        when (nInput) {
            "0" -> {
                println("Exit input a host config.")
                flag = false
                repeat = true
            }
            "1" -> {
                println("\n=====Input a host config=====\n")
                inputAndGenerate()
                flag = true
            }
        }
    } while ((nInput != "0" && nInput != "1") || flag)
}

fun inputAndGenerate() {
    var ip: String
    do {
        print("Enter version 4 IP: ")
        ip = scanner.next()
    } while (!ip.matches(regex.toRegex()))

    var port = 0
    do {
        var f = false
        try {
            print("Enter port: ")
            port = scanner.nextInt()
        } catch (ex: Exception) {
            println("Enter a positive integer")
            f = true
            scanner.next()
        }
    } while (f || port <= 0)

    var typeConnection = ""
    var tmp: String
    do {
        println("Choose type connection: ")
        println("1. WIFI\t2. DATA_4G\t3. DATA_5G\t4. Others")
        print("Your choice: ")
        tmp = scanner.next()

        when (tmp) {
            "1" -> typeConnection = "WIFI"
            "2" -> typeConnection = "DATA_4G"
            "3" -> typeConnection = "DATA_5G"
            "4" -> typeConnection = "Others"
        }
    } while (tmp != "1" && tmp != "2" && tmp != "3" && tmp != "4")

    listHostConfig.add(HostConfig(ip, port, typeConnection))
    listHostConfig.add(HostConfig(ip, port + 1, typeConnection))
    listHostConfig.add(HostConfig(ip, port + 2, typeConnection))
    listHostConfig.add(HostConfig(ip, port + 3, typeConnection))
    println("Input and generate successfully")
}
