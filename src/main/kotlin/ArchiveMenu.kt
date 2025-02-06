class ArchiveMenu : Menu() {
    private val archives = mutableListOf<Archive>()

    init {
        menuItems.add("Создать архив" to ::createArchive)
        menuItems.add("Выход" to ::exit)
    }

    override fun show() {
        while (true) {
            menuItems.clear()
            menuItems.add("Создать архив" to ::createArchive)
            archives.forEachIndexed { index, archive ->
                menuItems.add("${archive.name}" to { openArchive(index) })
            }
            menuItems.add("Выход" to ::exit)
            handleUserInput()
        }
    }

    private fun createArchive() {
        println("Введите название архива:")
        val name = scanner.nextLine()
        if (name.isBlank()) {
            println("Ошибка: название архива не может быть пустым.")
            return
        }
        archives.add(Archive(name))
        println("Архив \"$name\" создан.")
    }

    private fun openArchive( index: Int) {
        val archive = archives[index]
        val noteMenu = NoteMenu(archive, this)
        noteMenu.show()
    }

    private fun exit() {
        println("Выход из программы.")
        System.exit(0)
    }
}