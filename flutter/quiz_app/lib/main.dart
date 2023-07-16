import "package:flutter/material.dart";

import "question_model.dart";
import './quiz.dart';
import './result.dart';

void main() => runApp(const MyApp());

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  var _index = 0;
  var _score = 0;

  final questions = [
    QuestionModel("What's my favorite color?",
        ["matte black", "navy blue", "olive green", "blood red"], 1),
    QuestionModel("What's my favorite language?",
        ["english", "hindi", "telugu", "tamil"], 1),
    QuestionModel("What's my favorite song genre?",
        ["hard metal", "hard rock", "country side", "hip hop"], 1),
    QuestionModel("What's my favorite series?",
        ["peaky blinders", "the witcher", "breaking bad", "mr. robot"], 3),
    QuestionModel("What's my favorite sports?",
        ["basketball", "badminton", "cricket", "volleyball"], 1),
    QuestionModel("What's my favorite noddles?",
        ["maggie", "yippee", "chings", "wai wai"], 2),
  ];

  void _resetQuiz() {
    setState(() {
      _score = 0;
      _index = 0;
    });
  }

  void _onClick(var answer) {
    QuestionModel question = questions.elementAt(_index);
    String correctAnswer = question.options.elementAt(question.answer);
    if (answer == correctAnswer) {
      _score++;
    }
    setState(() {
      _index++;
    });
    debugPrint('userAnswer: $answer');
    debugPrint('index: $_index');
    if (_index >= questions.length) {
      debugPrint('quiz completed');
      debugPrint('score: $_score');
    }
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
          appBar: AppBar(
            title: const Text("Quiz App"),
          ),
          body: _index < questions.length
              ? Quiz(questions.elementAt(_index), _onClick)
              : Result((_score / questions.length) * 100, _resetQuiz)),
    );
  }
}
