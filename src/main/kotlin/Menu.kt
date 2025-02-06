import java.util.Scanner

abstract class Menu {
    protected val scanner = Scanner(System.`in`)
    protected val menuItems = mutableListOf<Pair<String, () -> Unit>>()

    abstract fun show()

    protected fun displayMenu() {
        println("Выберите пункт меню:")
        menuItems.forEachIndexed { index, pair ->
            println("$index. ${pair.first}")
        }
    }

    protected fun handleUserInput() {
        while (true) {
            displayMenu()
            val input = scanner.nextLine()
            if (input.isBlank() || !input.all { it.isDigit() }) {
                println("Ошибка: введите цифру.")
                continue
            }
            val choice = input.toInt()
            if (choice < 0 || choice >= menuItems.size) {
                println("Ошибка: такого пункта нет.")
                continue
            }
            menuItems[choice].second()
            break
        }
    }
}