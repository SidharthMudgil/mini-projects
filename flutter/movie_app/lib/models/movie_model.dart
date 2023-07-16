class MovieModel {
  final String _img;
  final String _title;
  final String _genres;
  final double _rating;

  const MovieModel(this._img, this._title, this._genres, this._rating);

  factory MovieModel.fromJson(Map<String, dynamic> json) {
    return MovieModel(
      json['Poster'],
      json['Title'],
      json['Genre'],
      double.parse(json['imdbRating']),
    );
  }

  double get rating => _rating;

  String get genres => _genres;

  String get title => _title;

  String get img => _img;
}
