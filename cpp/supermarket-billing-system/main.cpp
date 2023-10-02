#include <iostream>
#include <fstream>
#include <iomanip>
#include <cstdio>
using namespace std;

class Shopping {
private:
    int pcode;
    float price;
    float discount;
    string pname;

public:
    void menu();
    void administration();
    void customer();
    void add();
    void edit();
    void removeProduct();
    void list();
    void receipt();
};

void Shopping::menu() {
    int choice;
    string email;
    string password;

    while (true) {
        cout << "\t\t\t\t\t_______________________________________________" << endl;
        cout << "\t\t\t\t\t                                               " << endl;
        cout << "\t\t\t\t\t_____________SUPERMARKET MAIN MENU_____________" << endl;
        cout << "\t\t\t\t\t                                               " << endl;
        cout << "\t\t\t\t\t_______________________________________________" << endl;
        cout << "\t\t\t\t\t                                               " << endl;
        cout << "\t\t\t\t               1) Administrator                  " << endl;
        cout << "\t\t\t\t                                                 " << endl;
        cout << "\t\t\t\t               2) Customer                       " << endl;
        cout << "\t\t\t\t                                                 " << endl;
        cout << "\t\t\t\t               3) Exit                           " << endl;
        cout << "\t\t\t\t                                                 " << endl;
        cout << "\t\t\t\t Please select: " << endl;
        cin >> choice;
        switch (choice) {
        case 1:
            cout << "\t\t\t\t   Please login" << endl;
            cout << "\t\t\t\t   Enter Email ID" << endl;
            cin >> email;
            cout << "\t\t\t\t    Password   " << endl;
            cin >> password;
            if (email == "prekshamahajan2020@gmail.com" && password == "Project") {
                administration();
            }
            else {
                cout << "Invalid Email ID/Password" << endl;
            }
            break;
        case 2:
            customer();
            break;
        case 3:
            exit(0);
            break;
        default:
            cout << "Please select from the above given options." << endl;
        }
    }
}

void Shopping::administration() {
    int choice;
    while (true) {
        cout << "\n\n\n\t\t\t Administrator menu";
        cout << "\n\t\t\t|______1) Add the product_______|";
        cout << "\n\t\t\t|                               |";
        cout << "\n\t\t\t|______2) Modify the product____|";
        cout << "\n\t\t\t|                               |";
        cout << "\n\t\t\t|______3) Delete the product____|";
        cout << "\n\t\t\t|                               |";
        cout << "\n\t\t\t|______4) Back to main menu_____|";
        cout << "\n\n\t Please enter your choice: ";
        cin >> choice;
        switch (choice) {
        case 1:
            add();
            break;
        case 2:
            edit();
            break;
        case 3:
            removeProduct();
            break;
        case 4:
            menu();
            break;
        default:
            cout << "Invalid choice!";
        }
    }
}

void Shopping::customer() {
    int choice;
    while (true) {
        cout << "\t\t\t    Customer    \n";
        cout << "\t\t\t________________\n";
        cout << "    1) Buy Product    \n";
        cout << "\t\t\t________________\n";
        cout << "    2) Go back    " << endl;
        cout << "\t\t\t Enter your choice: ";
        cin >> choice;
        switch (choice) {
        case 1:
            receipt();
            break;
        case 2:
            menu();
            break;
        default:
            cout << "Invalid choice!";
        }
    }
}

void Shopping::add() {
    fstream data;
    int c;
    int token = 0;
    float p, d;
    string n;
    cout << "\n\n\t\t\t  Add new product ";
    cout << "\n\n\t Product code of the product ";
    cin >> pcode;
    cout << "\n\n Name of the product  ";
    cin >> pname;
    cout << "\n\n\t Price of the product ";
    cin >> price;
    data.open("database.txt", ios::in);
    if (!data) {
        data.open("database.txt", ios::app | ios::out);
        data << pcode << " " << pname << " " << price << " " << discount << endl;
        data.close();
    }
    else {
        data >> c >> n >> p >> d;
        while (!data.eof()) {
            if (c == pcode) {
                token++;
            }
            data >> c >> n >> p >> d;
        }
        data.close();

        if (token == 1) {
            cout << "\n\n\t\t Record with the same product code already exists! Please try again.";
            return;
        }
        else {
            data.open("database.txt", ios::app | ios::out);
            data << pcode << " " << pname << " " << price << " " << discount << endl;
            data.close();
        }
        cout << "\n\n\t\t Record Inserted ";
    }
}

void Shopping::edit() {
    fstream data, data1;
    int pkey, token = 0, c;
    float p, d;
    string n;

    cout << "\n\t\t\t Modify the record ";
    cout << "\n\t\t\t Enter the product code: ";
    cin >> pkey;

    data.open("database.txt", ios::in);
    if (!data) {
        cout << "\n\nFile doesn't exist! ";
    }
    else {
        data1.open("database1.txt", ios::app | ios::out);
        data >> pcode >> pname >> price >> discount;

        while (!data.eof()) {
            if (pkey == pcode) {
                cout << "\n\t\t Product of new code: ";
                cin >> c;
                cout << "\n\t\t Name of the product: ";
                cin >> n;
                cout << "\n\t\t Price: ";
                cin >> p;
                cout << "\n\t\t Discount: ";
                cin >> d;
                data1 << c << " " << n << " " << p << " " << d << endl;
                cout << "\n\t\t Record edited ";
                token++;
            }
            else {
                data1 << pcode << " " << pname << " " << price << " " << discount << endl;
            }
            data >> pcode >> pname >> price >> discount;
        }
        data.close();
        data1.close();

        removeProduct();

        if (token == 0) {
            cout << "\n\n  Record not found, sorry!";
        }
    }
}

void Shopping::removeProduct() {
    int pkey, token = 0;

    cout << "\n\n  Delete Product";
    cout << "\n\n  Product Code: ";
    cin >> pkey;

    fstream data, data1;
    data.open("database.txt", ios::in);
    if (!data) {
        cout << "File doesn't exist.";
    }
    else {
        data1.open("database1.txt", ios::app | ios::out);
        data >> pcode >> pname >> price >> discount;

        while (!data.eof()) {
            if (pcode == pkey) {
                cout << "\n\n\t Product deleted successfully";
                token++;
            }
            else {
                data1 << pcode << " " << pname << " " << price << " " << discount << endl;
            }
            data >> pcode >> pname >> price >> discount;
        }
        data.close();
        data1.close();
        remove("database.txt");
        rename("database1.txt", "database.txt");

        if (token == 0) {
            cout << "\n\n  Record not found!";
        }
    }
}

void Shopping::list() {
    fstream data;
    data.open("database.txt", ios::in);

    cout << "\n\n _____________________________________\n";
    cout << "Product No.\t\tName\t\tPrice\n";
    cout << "\n\n _____________________________________\n";
    data >> pcode >> pname >> price >> discount;

    while (!data.eof()) {
        cout << pcode << "\t\t" << pname << "\t\t" << price << endl;
        data >> pcode >> pname >> price >> discount;
    }
    data.close();
}

void Shopping::receipt() {
    fstream data;
    int arrCodes[100], arrQuantity[100];
    char choice;
    int c = 0;
    float amount = 0, total = 0;

    cout << "\n\n\t\t\t\t RECEIPT";
    data.open("database.txt", ios::in);
    if (!data) {
        cout << "\n\n Empty Database!";
    }
    else {
        data.close();
        list();
        cout << "\n_____________________________________ \n";
        cout << "\n                                      \n";
        cout << "\n       Please place the order         \n";
        cout << "\n                                      \n";
        cout << "\n_____________________________________ \n";

        do {
            m:
            cout << "\n\n Enter Product Code: ";
            cin >> arrCodes[c];
            cout << "\n\n Enter Product Quantity: ";
            cin >> arrQuantity[c];

            for (int i = 0; i < c; i++) {
                if (arrCodes[c] == arrCodes[i]) {
                    cout << "\n\n Duplicate Product Code. Please try again!";
                    goto m;
                }
            }
            c++;
            cout << "\n\n Do you want to buy another product? If yes, then press 'Y' else press 'N'";
            cin >> choice;
        } while (choice == 'Y' || choice == 'y');

        cout << "\n\n\t\t\t_______________RECEIPT_______________" << endl;
        cout << "\nProduct No.\tProduct Name \tProduct Quantity \tPrice \tAmount\n";

        for (int i = 0; i < c; i++) {
            data.open("database.txt", ios::in);
            data >> pcode >> pname >> price >> discount;

            while (!data.eof()) {
                if (pcode == arrCodes[i]) {
                    amount = price * arrQuantity[i];
                    total += amount;

                    cout << "\n" << pcode << "\t\t" << pname << "\t\t" << arrQuantity[i] << "\t\t" << price << "\t\t" << amount;
                }

                data >> pcode >> pname >> price >> discount;
            }

            data.close();
        }

        cout << "\n\n______________________________________";
        cout << "\n Total Amount: " << total;
    }
}

int main() {
    Shopping S;
    S.menu();
    return 0;
}