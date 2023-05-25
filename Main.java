import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class User {
    private String Ad;
    private String Soyad;
    private String KullaniciAdi;
    private String Parola;
    private String TcKimlikNo;
    private String TelefonNo;
    private String Email;
    private String Adres;
    private String userType;

    public User(String Ad, String Soyad, String KullaniciAdi, String Parola, String TcKimlikNo, String TelefonNo, String Email, String Adres, String userType) {
        this.Ad = Ad;
        this.Soyad = Soyad;
        this.KullaniciAdi = KullaniciAdi;
        this.Parola = Parola;
        this.TcKimlikNo = TcKimlikNo;
        this.TelefonNo = TelefonNo;
        this.Email = Email;
        this.Adres = Adres;
        this.userType = userType;
    }

    // Getters

    public String getAd() {
        return Ad;
    }

    public String getSoyad() {
        return Soyad;
    }

    public String getKullaniciAdi() {
        return KullaniciAdi;
    }

    public String getParola() {
        return Parola;
    }

    public String getTcKimlikNo() {
        return TcKimlikNo;
    }

    public String getTelefonNo() {
        return TelefonNo;
    }

    public String getEmail() {
        return Email;
    }

    public String getAdres() {
        return Adres;
    }

    public String getUserType() {
        return userType;
    }
}

class Event {
    private String title;
    private LocalDateTime dateTime;

    public Event(String title, LocalDateTime dateTime) {
        this.title = title;
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}

class Calendar {
    private List<Event> events;

    public Calendar() {
        events = new ArrayList<>();
    }

    public void addEvent(String title, LocalDateTime dateTime) {
        Event event = new Event(title, dateTime);
        events.add(event);
        System.out.println("Etkinlik Eklendi: " + event.getTitle());
    }

    public void viewEvents(LocalDate date) {
        System.out.println("Etkinlikler" + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ":");
        for (Event event : events) {
            if (event.getDateTime().toLocalDate().equals(date)) {
                System.out.println(event.getTitle() + " da " + event.getDateTime().format(DateTimeFormatter.ofPattern("HH:mm")));
            }
        }
    }

    public void setReminder(int minutes) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime reminderTime = now.plusMinutes(minutes);

        for (Event event : events) {
            LocalDateTime eventDateTime = event.getDateTime();
            if (eventDateTime.isAfter(now) && eventDateTime.isBefore(reminderTime)) {
                System.out.println("Hatýrlatma: " + event.getTitle() + " da " + eventDateTime.format(DateTimeFormatter.ofPattern("HH:mm")));
            }
        }
    }
}

class KullaniciKaydi {
    private Map<String, User> users;

    public KullaniciKaydi() {
        users = new HashMap<>();
    }

    public void registerUser(String Ad, String Soyad, String KullaniciAdi, String Parola, String TcKimlikNo, String TelefonNo, String Email, String Adres, String userType) {
        User user = new User(Ad, Soyad, KullaniciAdi, Parola, TcKimlikNo, TelefonNo, Email, Adres, userType);
        users.put(KullaniciAdi, user);
        System.out.println("Kullanýcý Kayýtlarý: " + KullaniciAdi);
    }

    public User getKullaniciAdinaGore(String KullaniciAdi) {
        return users.get(KullaniciAdi);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        KullaniciKaydi kullaniciKaydi = new KullaniciKaydi();
        Calendar calendar = new Calendar();

        System.out.print("Ad: ");
        String Ad = scanner.nextLine();
        System.out.print("Soyad: ");
        String Soyad = scanner.nextLine();
        System.out.print("KullaniciAdi: ");
        String KullaniciAdi = scanner.nextLine();
        System.out.print("Parola: ");
        String Parola = scanner.nextLine();
        System.out.print("TC Kimlik No: ");
        String TcKimlikNo = scanner.nextLine();
        System.out.print("Telefon No: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Email: ");
        String Email = scanner.nextLine();
        System.out.print("Adres: ");
        String Adres = scanner.nextLine();
        System.out.print("User type: ");
        String userType = scanner.nextLine();

        kullaniciKaydi.registerUser(Ad, Soyad, KullaniciAdi, Parola, TcKimlikNo, phoneNumber, Email, Adres, userType);

        while (true) {
            System.out.println("1. Etkinlik Ekle");
            System.out.println("2. Bir Tarih Ýçin Etkinlikleri Görüntüleyin");
            System.out.println("3. Hatýrlatýcý Ayarla");
            System.out.println("0. Çýkýþ");
            System.out.print("Seçiminizi Giriniz: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("Çýkýþ Yapýlýyor...");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Etkinlik Baþlýðýný Giriniz: ");
                    String title = scanner.nextLine();
                    System.out.print("Etkinlik Tarihini ve Saatini Giriniz (yyyy-MM-dd HH:mm): ");
                    String dateTimeStr = scanner.nextLine();
                    LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    calendar.addEvent(title, dateTime);
                    break;
                case 2:
                    System.out.print("Tarih Giriniz (yyyy-MM-dd): ");
                    String dateStr = scanner.nextLine();
                    LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    calendar.viewEvents(date);
                    break;
                case 3:
                    System.out.print("Hatýrlatma Dakikasýný Giriniz: ");
                    int minutes = scanner.nextInt();
                    scanner.nextLine();
                    calendar.setReminder(minutes);
                    break;
                default:
                    System.out.println("Geçersiz Ýþlem!");
            }

            System.out.println();
        }

        scanner.close();
    }
}

