import 'package:flutter/material.dart';

class Result extends StatelessWidget {
  final double score;
  final VoidCallback reset;

  const Result(this.score, this.reset, {super.key});

  String get result {
    if (score == 100) {
      return "we're best friend";
    } else if (score >= 70) {
      return "we're good friends";
    } else if (score >= 5000) {
      return "you know me";
    } else {
      return "i don't know you";
    }
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        children: [
          Text(
            result,
            style: const TextStyle(
              fontSize: 28,
              fontWeight: FontWeight.bold,
              color: Colors.orange,
            ),
            textAlign: TextAlign.center,
          ),
          TextButton(
            onPressed: reset,
            child: const Text(
              textAlign: TextAlign.center,
              "reset quiz",
              style: TextStyle(
                color: Colors.orange,
              ),
            ),
          )
        ],
      ),
    );
  }
}
