import 'dart:convert';

import 'package:http/http.dart' as http;

import '../models/movie_model.dart';

class RemoteServices {
  static var client = http.Client();

  static Future<MovieModel> _fetchMovie(String movieId) async {
    var response = await client.get(Uri.parse(
      'https://www.omdbapi.com/?apikey=9a5bd42e&i=$movieId&plot=full',
    ));

    if (response.statusCode == 200) {
      return MovieModel.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to load movie');
    }
  }

  static Future<List<MovieModel>> fetchMovies(String searchTitle) async {
    List<MovieModel> movies = [];

    final response = await client.get(
      Uri.parse('https://www.omdbapi.com/?apikey=9a5bd42e&s=$searchTitle'),
    );

    if (response.statusCode == 200) {
      final List<dynamic> jsonArray =
          jsonDecode(response.body)['Search'] as List;

      if (jsonArray.isNotEmpty) {
        for (var obj in jsonArray) {
          var movie = await _fetchMovie(obj['imdbID']);
          movies.add(movie);
        }
      }
      return movies;
    }
    throw Exception('Failed to load movie');
  }
}
