package Bib;
import java.util.ArrayList;
import java.util.Scanner;

class Book {
    String title;
    String author;
    boolean isCheckedOut;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isCheckedOut = false;
    }

    public void checkout() {
        this.isCheckedOut = true;
    }

    public void checkin() {
        this.isCheckedOut = false;
    }

    public void displayInfo() {
        System.out.println("Название: " + title);
        System.out.println("Автор: " + author);
        System.out.println("Доступна: " + (!isCheckedOut ? "Да" : "Нет"));
    }
}

class Reader {
    String name;
    ArrayList<Book> checkedOutBooks = new ArrayList<>();

    public Reader(String name) {
        this.name = name;
    }

    public void checkoutBook(Book book) {
        if (!book.isCheckedOut) {
            book.checkout();
            checkedOutBooks.add(book);
            System.out.println(name + " взял(а) книгу " + book.title);
        } else {
            System.out.println("Извините, " + book.title + " уже на руках.");
        }
    }

    public void checkinBook(Book book) {
        if (checkedOutBooks.contains(book)) {
            book.checkin();
            checkedOutBooks.remove(book);
            System.out.println(name + " вернул(а) книгу " + book.title);
        } else {
            System.out.println("Эта книга не у вас.");
        }
    }

    public void displayInfo() {
        System.out.println("Читатель: " + name);
        System.out.println("Книги на руках:");
        for (Book book : checkedOutBooks) {
            System.out.println("- " + book.title);
        }
    }
}

class Library {
    ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayAvailableBooks() {
        System.out.println("Доступные книги:");
        for (Book book : books) {
            if (!book.isCheckedOut) {
                book.displayInfo();
                System.out.println();
            }
        }
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }
}

public class zd1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Добавление книг в библиотеку
        System.out.println("Введите количество книг, которые хотите добавить:");
        int bookCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < bookCount; i++) {
            System.out.println("Введите название книги:");
            String title = scanner.nextLine();
            System.out.println("Введите автора книги:");
            String author = scanner.nextLine();
            library.addBook(new Book(title, author));
        }

        // Создание читателя
        System.out.println("Введите имя читателя:");
        String readerName = scanner.nextLine();
        Reader reader = new Reader(readerName);

        while (true) {
            System.out.println("1. Показать доступные книги\n2. Взять книгу\n3. Вернуть книгу\n4. Информация о читателе\n5. Выход");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    library.displayAvailableBooks();
                    break;
                case 2:
                    System.out.println("Введите название книги, которую хотите взять:");
                    String checkoutTitle = scanner.nextLine();
                    Book checkoutBook = library.findBookByTitle(checkoutTitle);
                    if (checkoutBook != null) {
                        reader.checkoutBook(checkoutBook);
                    } else {
                        System.out.println("Книга не найдена.");
                    }
                    break;
                case 3:
                    System.out.println("Введите название книги, которую хотите вернуть:");
                    String returnTitle = scanner.nextLine();
                    Book returnBook = library.findBookByTitle(returnTitle);
                    if (returnBook != null) {
                        reader.checkinBook(returnBook);
                    } else {
                        System.out.println("Книга не найдена.");
                    }
                    break;
                case 4:
                    reader.displayInfo();
                    break;
                case 5:
                    System.out.println("До свидания!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }
}
