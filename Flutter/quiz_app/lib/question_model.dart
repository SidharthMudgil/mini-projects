class QuestionModel {
  final String _question;
  final List<String> _options;
  final int _answer;

  QuestionModel(this._question, this._options, this._answer);

  List<String> get options => _options;

  String get question => _question;

  int get answer => _answer;
}
