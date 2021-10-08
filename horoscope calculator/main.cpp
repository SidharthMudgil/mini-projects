#include<iostream>
#include<conio.h>
#include<stdio.h>
#include<string.h>
#include<ctype.h>
#include<stdlib.h>
using namespace std;
struct future
{
float age;
char name[50];
char rashi[25];
char ratan[25];
char day[25];
char dhatu[25];
char quantity[25];


void enter()
{
_cprintf("                               Future Calculator");
cout<<endl;
_cprintf("For seeing your future please give informations respectively : ");
cout<<endl;
_cprintf("Enter your Age : ");
cin>>age;
_cprintf("Enter your Name : ");
cin>>name;
cout<<endl;
_cprintf("Your future is calculating");
cout<<endl<<endl;
}

void display()
{
_cprintf("Your rashi/horoscope is : ");
cout<<rashi;
cout<<endl;
_cprintf("Your lucky dinank/day is : ");
cout<<day;
cout<<endl;
_cprintf("Your lucky dhatu/metal is : ");
cout<<dhatu;
cout<<endl;
_cprintf("Your lucky ratan is : ");
cout<<ratan;
cout<<endl;
_cprintf("Your lucky weight for ratan is : ");
cout<<quantity;
cout<<endl;
}

void getcalc();
};

void future::getcalc()
{
int x=strlen(name);
for(int i=0;i<=x;i++)
{
if(name[0]=='c'&&name[1]=='h'&&name[2]=='u')
{
strcpy(rashi,"Aries (Mesh)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Ashit Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='c'&&name[1]=='h'&&name[2]=='e')
{
strcpy(rashi,"Aries (Mesh)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Ashit Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='c'&&name[1]=='h'&&name[2]=='o')
{
strcpy(rashi,"Aries (Mesh)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Ashit Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='l'&&name[1]=='a')
{
strcpy(rashi,"Aries (Mesh)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Ashit Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='l'&&name[1]=='e')
{
strcpy(rashi,"Aries (Mesh)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Ashit Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='l'&&name[1]=='i')
{
strcpy(rashi,"Aries (Mesh)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Ashit Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='l'&&name[1]=='u')
{
strcpy(rashi,"Aries (Mesh)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Ashit Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='l'&&name[1]=='o')
{
strcpy(rashi,"Aries (Mesh)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Ashit Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='a')
{
strcpy(rashi,"Aries (Mesh)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Ashit Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='e')
{
strcpy(rashi,"Taurus (vrish)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='u')
{
strcpy(rashi,"Taurus (vrish)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='e'&&name[1]=='e')
{
strcpy(rashi,"Taurus (vrish)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='o')
{
strcpy(rashi,"Taurus (vrish)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='v')
{
strcpy(rashi,"Taurus (vrish)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='v'&&name[1]=='a')
{
strcpy(rashi,"Taurus (vrish)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='v'&&name[1]=='i')
{
strcpy(rashi,"Taurus (vrish)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='v'&&name[1]=='u')
{
strcpy(rashi,"Taurus (vrish)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='v'&&name[1]=='a'&&name[2]=='y')
{
strcpy(rashi,"Taurus (vrish)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='v'&&name[1]=='o')
{
strcpy(rashi,"Taurus (vrish)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='k'&&name[1]=='a')
{
strcpy(rashi,"Gemini (Mithun)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wednesday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='k'&&name[1]=='i')
{
strcpy(rashi,"Gemini (Mithun)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wednesday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='k'&&name[1]=='u')
{
strcpy(rashi,"Gemini (Mithun)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wednesday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='g'&&name[1]=='h')
{
strcpy(rashi,"Gemini (Mithun)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wednesday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='a'&&name[1]=='n'&&name[2]=='g')
{
strcpy(rashi,"Gemini (Mithun)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wednesday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='c'&&name[1]=='h'&&name[2]=='h')
{
strcpy(rashi,"Gemini (Mithun)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wednesday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='k'&&name[1]=='e')
{
strcpy(rashi,"Gemini (Mithun)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wednesday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='k'&&name[1]=='o')
{
strcpy(rashi,"Gemini (Mithun)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wednesday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='h'&&name[1]=='a')
{
strcpy(rashi,"Gemini (Mithun)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wednesday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='h'&&name[1]=='i')
{
strcpy(rashi,"Cancer (Kark)");
strcpy(ratan,"Pearl (Moti)");
strcpy(day,"Monday (Somwar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='h'&&name[1]=='u')
{
strcpy(rashi,"Cancer (Kark)");
strcpy(ratan,"Pearl (Moti)");
strcpy(day,"Monday (Somwar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='h'&&name[1]=='e')
{
strcpy(rashi,"Cancer (Kark)");
strcpy(ratan,"Pearl (Moti)");
strcpy(day,"Monday (Somwar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='h'&&name[1]=='o')
{
strcpy(rashi,"Cancer (Kark)");
strcpy(ratan,"Pearl (Moti)");
strcpy(day,"Monday (Somwar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='d'&&name[1]=='a')
{
strcpy(rashi,"Cancer (Kark)");
strcpy(ratan,"Pearl (Moti)");
strcpy(day,"Monday (Somwar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='d'&&name[1]=='i')
{
strcpy(rashi,"Cancer (Kark)");
strcpy(ratan,"Pearl (Moti)");
strcpy(day,"Monday (Somwar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='d'&&name[1]=='u')
{
strcpy(rashi,"Cancer (Kark)");
strcpy(ratan,"Pearl (Moti)");
strcpy(day,"Monday (Somwar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='d'&&name[1]=='e')
{
strcpy(rashi,"Cancer (Kark)");
strcpy(ratan,"Pearl (Moti)");
strcpy(day,"Monday (Somwar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='d'&&name[1]=='o')
{
strcpy(rashi,"Cancer (Kark)");
strcpy(ratan,"Pearl (Moti)");
strcpy(day,"Monday (Somwar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='m'&&name[1]=='a')
{
strcpy(rashi,"Leo (Singh)");
strcpy(ratan,"Ruby (Manik)");
strcpy(day,"Sunday (Raviwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='m'&&name[1]=='i')
{
strcpy(rashi,"Leo (Singh)");
strcpy(ratan,"Ruby (Manik)");
strcpy(day,"Sunday (Raviwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='m'&&name[1]=='u')
{
strcpy(rashi,"Leo (Singh)");
strcpy(ratan,"Ruby (Manik)");
strcpy(day,"Sunday (Raviwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='m'&&name[1]=='e')
{
strcpy(rashi,"Leo (Singh)");
strcpy(ratan,"Ruby (Manik)");
strcpy(day,"Sunday (Raviwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='m'&&name[1]=='o')
{
strcpy(rashi,"Leo (Singh)");
strcpy(ratan,"Ruby (Manik)");
strcpy(day,"Sunday (Raviwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='t'&&name[1]=='a')
{
strcpy(rashi,"Leo (Singh)");
strcpy(ratan,"Ruby (Manik)");
strcpy(day,"Sunday (Raviwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='t'&&name[1]=='i')
{
strcpy(rashi,"Leo (Singh)");
strcpy(ratan,"Ruby (Manik)");
strcpy(day,"Sunday (Raviwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='t'&&name[1]=='u')
{
strcpy(rashi,"Leo (Singh)");
strcpy(ratan,"Ruby (Manik)");
strcpy(day,"Sunday (Raviwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='t'&&name[1]=='e')
{
strcpy(rashi,"Leo (Singh)");
strcpy(ratan,"Ruby (Manik)");
strcpy(day,"Sunday (Raviwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='t'&&name[1]=='o')
{
strcpy(rashi,"Virgo (Kanya)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wedneday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='p'&&name[1]=='a')
{
strcpy(rashi,"Virgo (Kanya)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wedneday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='p'&&name[1]=='i')
{
strcpy(rashi,"Virgo (Kanya)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wedneday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='p'&&name[1]=='u')
{
strcpy(rashi,"Virgo (Kanya)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wedneday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='s'&&name[1]=='h')
{
strcpy(rashi,"Virgo (Kanya)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wedneday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='n'&&name[1]=='a')
{
strcpy(rashi,"Virgo (Kanya)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wedneday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='t'&&name[1]=='h')
{
strcpy(rashi,"Virgo (Kanya)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wedneday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='p'&&name[1]=='e')
{
strcpy(rashi,"Virgo (Kanya)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wedneday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='p'&&name[1]=='o')
{
strcpy(rashi,"Virgo (Kanya)");
strcpy(ratan,"Emerald (Panna)");
strcpy(day,"Wedneday (Bhudhwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='r'&&name[1]=='a')
{
strcpy(rashi,"Libra (Tula)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='r'&&name[1]=='i')
{
strcpy(rashi,"Libra (Tula)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='r'&&name[1]=='u')
{
strcpy(rashi,"Libra (Tula)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='r'&&name[1]=='e')
{
strcpy(rashi,"Libra (Tula)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='r'&&name[1]=='o')
{
strcpy(rashi,"Libra (Tula)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='t'&&name[1]=='a')
{
strcpy(rashi,"Libra (Tula)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='t'&&name[1]=='i')
{
strcpy(rashi,"Libra (Tula)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='t'&&name[1]=='u')
{
strcpy(rashi,"Libra (Tula)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='t'&&name[1]=='e')
{
strcpy(rashi,"Libra (Tula)");
strcpy(ratan,"Opel (Opel)");
strcpy(day,"Friday (Shukrawar)");
strcpy(dhatu,"Asht Dhatu and Silver");
strcpy(quantity,"8-11 ct");
}
else if(name[0]=='t'&&name[1]=='o')
{
strcpy(rashi,"Scorpio (Vrishchik)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='n'&&name[1]=='a')
{
strcpy(rashi,"Scorpio (Vrishchik)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='n'&&name[1]=='i')
{
strcpy(rashi,"Scorpio (Vrishchik)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='n'&&name[1]=='u')
{
strcpy(rashi,"Scorpio (Vrishchik)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='n'&&name[1]=='e')
{
strcpy(rashi,"Scorpio (Vrishchik)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='n'&&name[1]=='o')
{
strcpy(rashi,"Scorpio (Vrishchik)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='y'&&name[1]=='a')
{
strcpy(rashi,"Scorpio (Vrishchik)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='y'&&name[1]=='i')
{
strcpy(rashi,"Scorpio (Vrishchik)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='u')
{
strcpy(rashi,"Scorpio (Vrishchik)");
strcpy(ratan,"Coral (Moonga)");
strcpy(day,"Tuesday (Mangalwar)");
strcpy(dhatu,"Asht Dhatu");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='y'&&name[1]=='e')
{
strcpy(rashi,"Sagittarius (Dhanu)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thursday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='y'&&name[1]=='o')
{
strcpy(rashi,"Sagittarius (Dhanu)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thursday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='b'&&name[1]=='h'&&name[2]=='a')
{
strcpy(rashi,"Sagittarius (Dhanu)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thursday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='b'&&name[1]=='h'&&name[2]=='i')
{
strcpy(rashi,"Sagittarius (Dhanu)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thursday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='b'&&name[1]=='h'&&name[2]=='u')
{
strcpy(rashi,"Sagittarius (Dhanu)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thursday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='d'&&name[1]=='h'&&name[2]=='a')
{
strcpy(rashi,"Sagittarius (Dhanu)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thursday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='f'&&name[1]=='a')
{
strcpy(rashi,"Sagittarius (Dhanu)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thursday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='d'&&name[1]=='h'&&name[2]=='a')
{
strcpy(rashi,"Sagittarius (Dhanu)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thursday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='b'&&name[1]=='h'&&name[2]=='e')
{
strcpy(rashi,"Sagittarius (Dhanu)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thursday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='b'&&name[1]=='h'&&name[2]=='o')
{
strcpy(rashi,"Capricom (Makkar)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='j'&&name[1]=='a')
{
strcpy(rashi,"Capricom (Makkar)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='k'&&name[1]=='h'&&name[2]=='i')
{
strcpy(rashi,"Capricom (Makkar)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='k'&&name[1]=='h'&&name[2]=='u')
{
strcpy(rashi,"Capricom (Makkar)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='k'&&name[1]=='h'&&name[2]=='e')
{
strcpy(rashi,"Capricom (Makkar)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='k'&&name[1]=='h'&&name[2]=='o')
{
strcpy(rashi,"Capricom (Makkar)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='b'&&name[1]=='h'&&name[2]=='i')
{
strcpy(rashi,"Capricom (Makkar)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='j'&&name[1]=='a')
{
strcpy(rashi,"Capricom (Makkar)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='k'&&name[1]=='h')
{
strcpy(rashi,"Capricom (Makkar)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='j'&&name[1]=='i')
{
strcpy(rashi,"Capricom (Makkar)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='g'&&name[1]=='a')
{
strcpy(rashi,"Capricom (Makkar)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='g'&&name[1]=='u')
{
strcpy(rashi,"Aguarius (Kumbh)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='g'&&name[1]=='a'&&name[2]=='y')
{
strcpy(rashi,"Aguarius (Kumbh)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='g'&&name[1]=='o')
{
strcpy(rashi,"Aguarius (Kumbh)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='s'&&name[1]=='a')
{
strcpy(rashi,"Aguarius (Kumbh)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='s'&&name[1]=='i')
{
strcpy(rashi,"Aguarius (Kumbh)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='s'&&name[1]=='u')
{
strcpy(rashi,"Aguarius (Kumbh)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='s')
{
strcpy(rashi,"Aguarius (Kumbh)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='s'&&name[1]=='o')
{
strcpy(rashi,"Aguarius (Kumbh)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='d'&&name[1]=='a')
{
strcpy(rashi,"Aguarius (Kumbh)");
strcpy(ratan,"Blue Sapphire (Neelam)");
strcpy(day,"Saturday (Shaniwar)");
strcpy(dhatu,"Panch Dhatu and Silver");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='d'&&name[1]=='i')
{
strcpy(rashi,"Pisces (Miet)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thrusday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='d'&&name[1]=='u')
{
strcpy(rashi,"Pisces (Miet)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thrusday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='t'&&name[1]=='h')
{
strcpy(rashi,"Pisces (Miet)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thrusday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='j'&&name[1]=='h')
{
strcpy(rashi,"Pisces (Miet)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thrusday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='y'&&name[1]=='n')
{
strcpy(rashi,"Pisces (Miet)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thrusday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='d'&&name[1]=='e')
{
strcpy(rashi,"Pisces (Miet)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thrusday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='d'&&name[1]=='o')
{
strcpy(rashi,"Pisces (Miet)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thrusday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='c'&&name[1]=='h'&&name[2]=='a')
{
strcpy(rashi,"Pisces (Miet)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thrusday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else if(name[0]=='c'&&name[1]=='h'&&name[2]=='i')
{
strcpy(rashi,"Pisces (Miet)");
strcpy(ratan,"Yellow Sapphire (Pukhraj)");
strcpy(day,"Thrusday (Guruwar)");
strcpy(dhatu,"Asht Dhatu and Gold");
strcpy(quantity,"6-8 ct");
}
else
{
strcpy(rashi,"No details found !");
strcpy(ratan,"No details found !");
strcpy(day,"No details found !");
strcpy(dhatu,"No details found !");
strcpy(quantity,"No details found !");
}
}
}

int main()
{
    int x=1;
    while(x!=0)
    {
        if(x==0)
        {
            cout<<"\nSorry for inconvenience";
        }
        else 
        {
            future f;
            f.enter();
            f.getcalc();
            f.display();
        }
        cout<<endl;
        cout<<"\t\t\t\t\t\t\t..........................."<<endl;
        cout<<"\t\t\t\t\t\t\t..... press 0 to exit ....."<<endl;
        cout<<"\t\t\t\t\t\t\t..........................."<<endl;
        cin>>x;
    }
    return (0);
}