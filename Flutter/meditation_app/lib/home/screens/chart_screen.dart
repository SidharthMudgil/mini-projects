import 'package:flutter/material.dart';

class ChartScreen extends StatelessWidget {
  const ChartScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const Center(
      child: Text(
        "Chart Page",
        style: TextStyle(
          color: Colors.white,
          fontSize: 24,
          fontFamily: 'sf',
        ),
      ),
    );
  }
}
