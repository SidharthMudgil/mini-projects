import 'package:flutter/material.dart';
import 'package:lg_buttons/constants/constants.dart';
import 'package:lg_buttons/screens/connection_screen.dart';
import 'package:lg_buttons/service/lg_service.dart';
import 'package:lg_buttons/widgets/lg_button.dart';

class HomeScreen extends StatefulWidget {
  static const route = "/";

  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("LG Buttons"),
        actions: [
          IconButton(
            onPressed: () {
              Navigator.of(context).pushNamed(ConnectionScreen.route);
            },
            icon: const Icon(Icons.connected_tv_rounded),
          ),
        ],
      ),
      body: GridView.builder(
        padding: const EdgeInsets.all(16.0),
        physics: const BouncingScrollPhysics(),
        gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 4,
          crossAxisSpacing: 16.0,
          mainAxisSpacing: 16.0,
          childAspectRatio: 0.5,
        ),
        itemCount: 4,
        itemBuilder: (context, index) {
          return SizedBox(
            child: LGButton(
              label: Constants.labels[index],
              onPressed: () {
                switch (index) {
                  case 0:
                    {
                      final snackbar = SnackBar(
                        content: const Text(
                          "Please confirm before proceeding!",
                          style: TextStyle(
                            color: Constants.blue200,
                            fontSize: 16
                          ),
                        ),
                        action: SnackBarAction(
                          label: "Confirm",
                          onPressed: () {
                            LGService.instance?.rebootLG();
                          },
                          textColor: Colors.white,
                          backgroundColor: Constants.blue200,
                        ),
                        backgroundColor: Constants.blue100,
                      );
                      ScaffoldMessenger.of(context).showSnackBar(snackbar);
                      break;
                    }
                  case 1:
                    {
                      LGService.instance?.moveToHomeCity();
                      break;
                    }
                  case 2:
                    {
                      LGService.instance?.moveOrbitMyCity();
                      break;
                    }
                  case 3:
                    {
                      LGService.instance?.showBubble();
                      break;
                    }
                }
              },
              color: Constants.lgColors[index],
            ),
          );
        },
      ),
    );
  }
}
