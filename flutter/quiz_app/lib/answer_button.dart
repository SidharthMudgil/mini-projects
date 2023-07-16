import 'package:flutter/material.dart';

class AnswerElevatedButton extends StatelessWidget {
  final String answer;
  final Function onClick;

  const AnswerElevatedButton(this.answer, this.onClick, {super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      width: double.infinity,
      margin: const EdgeInsets.fromLTRB(20, 0, 20, 5),
      child: ElevatedButton(
        style: const ButtonStyle(
          backgroundColor: MaterialStatePropertyAll(Colors.blueGrey),
        ),
        onPressed: () => onClick(answer),
        child: Text(answer),
      ),
    );
  }
}
