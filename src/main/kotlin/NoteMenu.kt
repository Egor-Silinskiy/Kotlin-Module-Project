class NoteMenu(private  val archive: Archive, private val archiveMenu: ArchiveMenu) : Menu() {

    init {
        menuItems.add("Создать заметку" to ::createNote)
        menuItems.add("Выход" to ::exit)
    }

    override fun show() {
        while (true) {
            menuItems.clear()
            menuItems.add("Создать заметку" to ::createNote)
            archive.notes.forEachIndexed { index, note ->
                menuItems.add("${note.title}" to { openNote(index) })
            }
            menuItems.add("Выход" to ::exit)
            handleUserInput()
        }
    }

    private fun
            createNote() {
        println("Введите название заметки:")
        val title = scanner.nextLine()
        if (title.isBlank()) {
            println("Ошибка: название заметки не может быть пустым.")
            return
        }
        println("Введите текст заметки:")
        val content = scanner.nextLine()
        if (content.isBlank()) {
            println("Ошибка: текст заметки не может быть пустым.")
            return
        }
        archive.notes.add(Note(title, content))
        println("Заметка \"$title\" создана.")
    }

    private fun openNote(index: Int) {
        val note = archive.notes[index]
        println("Заметка: ${note.title}")
        println("Текст: ${note.content}")
        println("Нажмите Enter, чтобы вернуться.")
        scanner.nextLine()
        show()
    }

    private fun exit() {
        println("Возврат к выбору архива.")
        archiveMenu.show()
    }
}