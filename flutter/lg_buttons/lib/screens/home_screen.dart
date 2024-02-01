import 'package:flutter/material.dart';
import 'package:lg_buttons/constants/constants.dart';
import 'package:lg_buttons/screens/connection_screen.dart';
import 'package:lg_buttons/widgets/lg_button.dart';

class HomeScreen extends StatelessWidget {
  static const route = "/";

  const HomeScreen({super.key});

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
              onPressed: () {},
              color: Constants.lgColors[index],
            ),
          );
        },
      ),
    );
  }
}
