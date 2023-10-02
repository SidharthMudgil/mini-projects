import 'package:flutter/material.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: MaterialApp(
        home: Scaffold(
          resizeToAvoidBottomInset: false,
          appBar: AppBar(
            title: const Text('Task2: Temperature Converter'),
            centerTitle: true,
          ),
          body: const UserInterface(),
        ),
      ),
    );
  }
}

class UserInterface extends StatefulWidget {
  const UserInterface({super.key});

  @override
  State<UserInterface> createState() => _UserInterfaceState();
}

class _UserInterfaceState extends State<UserInterface> {
  String cel = '';
  String fah = '';
  String kel = '';
  double displayCel = 0, displayFah = 0, displayKel = 0;

  void convert(String cel, String fah, String kel) {
    if (cel != '') {
      setState(() {
        displayCel = double.parse(cel);
        displayFah = 1.8 * displayCel + 32;
        displayKel = displayCel + 273.15;
        // let's roundoff to two decimal places
        displayFah = double.parse((displayFah).toStringAsFixed(2));
        displayKel = double.parse((displayKel).toStringAsFixed(2));
      });
    }
    if (fah != '') {
      setState(() {
        displayFah = double.parse(fah);
        displayCel = 0.56 * (displayFah - 32);
        displayKel = 0.56 * displayFah + 255;
        // let's roundoff to two decimal places
        displayCel = double.parse((displayCel).toStringAsFixed(2));
        displayKel = double.parse((displayKel).toStringAsFixed(2));
      });
    }
    if (kel != '') {
      setState(() {
        displayKel = double.parse(kel);
        displayCel = displayKel - 273.15;
        displayFah = 1.8 * displayKel - 459.67;
        // let's roundoff to two decimal places
        displayCel = double.parse((displayCel).toStringAsFixed(2));
        displayFah = double.parse((displayFah).toStringAsFixed(2));
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        const Padding(
          padding: EdgeInsets.fromLTRB(10, 40, 10, 30),
          child: Text(
            'Input temperature (any one)',
            style: TextStyle(
              color: Colors.black,
              fontSize: 25,
              fontWeight: FontWeight.bold,
            ),
          ),
        ),
        Padding(
          padding: const EdgeInsets.all(10.0),
          child: Card(
            color: Colors.cyan,
            child: ListTile(
              leading: const Text(
                'Celsius',
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 20,
                ),
              ),
              title: Padding(
                padding: const EdgeInsets.symmetric(
                  horizontal: 50,
                ),
                child: TextField(
                  textAlign: TextAlign.center,
                  onChanged: (value) {
                    cel = value;
                  },
                ),
              ),
            ),
          ),
        ),
        Padding(
          padding: const EdgeInsets.all(10.0),
          child: Card(
            color: Colors.cyan,
            child: ListTile(
              leading: const Text(
                'Fahrenheit',
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 20,
                ),
              ),
              title: Padding(
                padding: const EdgeInsets.symmetric(
                  horizontal: 40,
                ),
                child: TextField(
                  textAlign: TextAlign.center,
                  onChanged: (value) {
                    fah = value;
                  },
                ),
              ),
            ),
          ),
        ),
        Padding(
          padding: const EdgeInsets.all(10.0),
          child: Card(
            color: Colors.cyan,
            child: ListTile(
              leading: const Text(
                'Kelvin',
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 20,
                ),
              ),
              title: Padding(
                padding: const EdgeInsets.symmetric(
                  horizontal: 60,
                ),
                child: TextField(
                  textAlign: TextAlign.center,
                  onChanged: (value) {
                    kel = value;
                  },
                ),
              ),
            ),
          ),
        ),
        const SizedBox(height: 20),
        Padding(
          padding: const EdgeInsets.all(20.0),
          child: ElevatedButton(
            onPressed: () {
              convert(cel, fah, kel);
            },
            child: const Padding(
              padding: EdgeInsets.all(15.0),
              child: Text(
                "convert",
                style: TextStyle(
                  fontSize: 17,
                ),
              ),
            ),
          ),
        ),
        const SizedBox(height: 20),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: [
            Card(
              color: Colors.cyan,
              child: Column(
                children: [
                  const Padding(
                    padding: EdgeInsets.all(20.0),
                    child: Text(
                      'Celsius',
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                      ),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.fromLTRB(15, 0, 15, 15),
                    child: Text(
                      '$displayCel',
                      style: const TextStyle(
                        fontSize: 25,
                      ),
                    ),
                  ),
                ],
              ),
            ),
            Card(
              color: Colors.cyan,
              child: Column(
                children: [
                  const Padding(
                    padding: EdgeInsets.all(20.0),
                    child: Text(
                      'Fahrenheit',
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                      ),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.fromLTRB(15, 0, 15, 15),
                    child: Text(
                      '$displayFah',
                      style: const TextStyle(
                        fontSize: 25,
                      ),
                    ),
                  ),
                ],
              ),
            ),
            Card(
              color: Colors.cyan,
              child: Column(
                children: [
                  const Padding(
                    padding: EdgeInsets.all(20.0),
                    child: Text(
                      'Kelvin',
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                      ),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.fromLTRB(15, 0, 15, 15),
                    child: Text(
                      '$displayKel',
                      style: const TextStyle(
                        fontSize: 25,
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ],
    );
  }
}