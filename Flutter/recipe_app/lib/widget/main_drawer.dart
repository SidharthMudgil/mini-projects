import 'package:flutter/material.dart';
import 'package:recipe_app/screen/tab_screen.dart';

import '../screen/settings_screen.dart';
import 'menu_item.dart';

class MainDrawer extends StatelessWidget {
  const MainDrawer({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: Column(
        children: [
          Container(
            padding: const EdgeInsets.all(10),
            color: Theme.of(context).colorScheme.secondary,
            alignment: Alignment.bottomLeft,
            height: MediaQuery.of(context).size.width * 0.3,
            child: const Text(
              'Recipe App',
              style: TextStyle(
                color: Colors.white,
                fontSize: 28,
                fontWeight: FontWeight.w600,
              ),
            ),
          ),
          DrawerItem('Categories', Icons.category, () {
            Navigator.of(context).pushReplacementNamed(TabScreen.routeName);
          }),
          DrawerItem('Settings', Icons.settings, () {
            Navigator.of(context).pushReplacementNamed(SettingsScreen.routeName);
          }),
        ],
      ),
    );
  }
}
