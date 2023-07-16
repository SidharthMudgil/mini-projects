class RecommendedModel {
  final String _title;
  final String _subtitle;
  final List<String> _assets;

  const RecommendedModel(this._title, this._subtitle, this._assets);

  List<String> get assets => _assets;

  String get title => _title;

  String get subtitle => _subtitle;
}
