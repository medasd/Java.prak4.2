package Car;
import java.util.ArrayList;
import java.util.Scanner;

class Car {
    String licensePlate;
    String model;
    boolean isRented;

    public Car(String licensePlate, String model) {
        this.licensePlate = licensePlate;
        this.model = model;
        this.isRented = false;
    }

    public void rent() {
        this.isRented = true;
    }

    public void returnCar() {
        this.isRented = false;
    }

    public void displayInfo() {
        System.out.println("Модель: " + model);
        System.out.println("Номерной знак: " + licensePlate);
        System.out.println("Доступен: " + (!isRented ? "Да" : "Нет"));
    }
}

class Driver {
    String name;
    Car rentedCar = null;

    public Driver(String name) {
        this.name = name;
    }

    public void rentCar(Car car) {
        if (!car.isRented) {
            car.rent();
            this.rentedCar = car;
            System.out.println(name + " арендовал(а) автомобиль " + car.model);
        } else {
            System.out.println("Извините, автомобиль " + car.model + " уже арендован.");
        }
    }

    public void returnCar() {
        if (rentedCar != null) {
            rentedCar.returnCar();
            System.out.println(name + " вернул(а) автомобиль " + rentedCar.model);
            rentedCar = null;
        } else {
            System.out.println("У вас нет арендованного автомобиля.");
        }
    }

    public void displayInfo() {
        System.out.println("Водитель: " + name);
        if (rentedCar != null) {
            System.out.println("Арендованный автомобиль: " + rentedCar.model);
        } else {
            System.out.println("Автомобиль не арендован.");
        }
    }
}

class CarPark {
    ArrayList<Car> cars = new ArrayList<>();

    public void addCar(Car car) {
        cars.add(car);
    }

    public void displayAvailableCars() {
        System.out.println("Доступные автомобили:");
        for (Car car : cars) {
            if (!car.isRented) {
                car.displayInfo();
                System.out.println();
            }
        }
    }

    public Car findCarByModel(String model) {
        for (Car car : cars) {
            if (car.model.equalsIgnoreCase(model)) {
                return car;
            }
        }
        return null;
    }
}

public class zd2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarPark carPark = new CarPark();

        // Добавление автомобилей в автопарк
        System.out.println("Введите количество автомобилей, которые хотите добавить:");
        int carCount = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < carCount; i++) {
            System.out.println("Введите модель автомобиля:");
            String model = scanner.nextLine();
            System.out.println("Введите номерной знак автомобиля:");
            String licensePlate = scanner.nextLine();
            carPark.addCar(new Car(licensePlate, model));
        }

        // Создание водителя
        System.out.println("Введите имя водителя:");
        String driverName = scanner.nextLine();
        Driver driver = new Driver(driverName);

        while (true) {
            System.out.println("1. Показать доступные автомобили\n2. Арендовать автомобиль\n3. Вернуть автомобиль\n4. Информация о водителе\n5. Выход");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    carPark.displayAvailableCars();
                    break;
                case 2:
                    System.out.println("Введите модель автомобиля, который хотите арендовать:");
                    String rentModel = scanner.nextLine();
                    Car rentCar = carPark.findCarByModel(rentModel);
                    if (rentCar != null) {
                        driver.rentCar(rentCar);
                    } else {
                        System.out.println("Автомобиль не найден.");
                    }
                    break;
                case 3:
                    driver.returnCar();
                    break;
                case 4:
                    driver.displayInfo();
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

