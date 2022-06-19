package me.emokid.command;

public final class Label {
    // output formatting constants

    public static final String HELP_DESCRIPTION = "Вывести справку по доступным командам";
    public static final String HELP_HEADER = "Доступные команды:";
    public static final String INFO_DESCRIPTION = "Вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    public static final String INFO_HEADER = "Информация о коллекции:";
    public static final String INFO_PREFIX1 = "Тип коллекции:";
    public static final String INFO_PREFIX2 = "Дата инициализации:";
    public static final String INFO_PREFIX3 = "Количество элементов:";
    public static final String SHOW_DESCRIPTION = "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    public static final String SHOW_IF_EMPTY = "Коллекция пуста!";
    public static final String INSERT_DESCRIPTION = "Добавить новый элемент с заданным ключом";
    public static final String UPDATE_DESCRIPTION = "Обновить значение элемента коллекции, id которого равен заданному";
    public static final String REMOVE_KEY_DESCRIPTION = "Удалить элемент из коллекции по его ключу";
    public static final String CLEAR_DESCRIPTION = "Очистить коллекцию";
    public static final String SAVE_DESCRIPTION = "Сохранить коллекцию в файл";
    public static final String EXECUTE_SCRIPT_DESCRIPTION = "Считать и исполнить скрипт из указанного файла.";
    public static final String EXIT_DESCRIPTION = "Завершить программу (без сохранения в файл)";
    public static final String REMOVE_GREATER_DESCRIPTION = "Удалить из коллекции все элементы, превышающие заданный";
    public static final String HISTORY_DESCRIPTION = "Вывести последние 5 команд (без их аргументов)";
    public static final String HISTORY_HEADER = "Последние 5 команд (без их аргументов):";
    public static final String HISTORY_IF_EMPTY = "История пуста!";
    public static final String REPLACE_IF_LOWE_DESCRIPTION = "Заменить значение по ключу, если новое значение меньше старого";
    public static final String REMOVE_ALL_BY_MINUTES_OF_WAITING_DESCRIPTION = "Удалить из коллекции все элементы, значение поля minutesOfWaiting которого эквивалентно заданному";
    public static final String FILTER_CONTAINS_SOUNDTRACK_NAME_DESCRIPTION = "Вывести элементы, значение поля soundtrackName которых содержит заданную подстроку";
    public static final String PRINT_DESCENDING_DESCRIPTION = "Вывести элементы коллекции в порядке убывания";
    public static final String PRINT_DESCENDING_HEADER = "Доступные элементы коллекции в порядке убывания:";
    public static final String PRINT_DESCENDING_IF_EMPTY = "Коллекция пуста!";

    public static final String SUCCESSFUL = "Команда выполнена";
    public static final String SHOW_HEADER = "Доступные элементы коллекции:";
    public static final String ENVIRONMENT_VARIABLE_IS_FOUND = "JSON файл обнаружен по следующему пути:";
    public static final Object DATA_IS_LOADED_SUCCESSFUL = "Данные загружены!";
}
