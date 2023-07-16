import 'package:flutter/material.dart';

import './question_text.dart';
import './answer_button.dart';
import './question_model.dart';

class Quiz extends StatelessWidget {
  final QuestionModel question;
  final Function func;

  const Quiz(this.question, this.func, {super.key});

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        children: [
          QuestionText(question.question),
          AnswerElevatedButton(question.options.elementAt(0), func),
          AnswerElevatedButton(question.options.elementAt(1), func),
          AnswerElevatedButton(question.options.elementAt(2), func),
          AnswerElevatedButton(question.options.elementAt(3), func),
        ],
      ),
    );
  }
}
