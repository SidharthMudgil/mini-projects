import 'package:flutter/material.dart';

class ProfileScreen extends StatelessWidget {
  const ProfileScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const Center(
      child: Text(
        "Profile Page",
        style: TextStyle(
          color: Colors.white,
          fontSize: 24,
          fontFamily: 'sf',
        ),
      ),
    );
  }
}
