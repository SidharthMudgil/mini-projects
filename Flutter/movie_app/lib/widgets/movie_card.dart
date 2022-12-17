import 'package:flutter/material.dart';

import '../models/movie_model.dart';

class MovieCard extends StatelessWidget {
  final MovieModel _movie;

  const MovieCard(this._movie, {super.key});

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: const EdgeInsets.only(bottom: 15),
      elevation: 5,
      child: Container(
        padding: const EdgeInsets.fromLTRB(10, 10, 10, 20),
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.center,
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            ClipRRect(
              borderRadius: BorderRadius.circular(10),
              child: Image.network(
                _movie.img,
                height: 160,
                width: 120,
                fit: BoxFit.cover,
              ),
            ),
            const SizedBox(
              width: 20,
            ),
            Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                SizedBox(
                  width: 200,
                  child: Text(
                    _movie.title,
                    style: const TextStyle(
                      fontFamily: 'sf',
                      fontSize: 22,
                      fontWeight: FontWeight.w500,
                    ),
                    textAlign: TextAlign.start,
                  ),
                ),
                SizedBox(
                  width: 200,
                  child: Text(
                    _movie.genres,
                    style: const TextStyle(
                      fontFamily: 'sf',
                      fontSize: 14,
                    ),
                    textAlign: TextAlign.start,
                  ),
                ),
                const SizedBox(
                  height: 5,
                ),
                Container(
                  padding:
                      const EdgeInsets.symmetric(vertical: 5, horizontal: 10),
                  decoration: BoxDecoration(
                    color: (_movie.rating > 7.0) ? Colors.green : Colors.blue,
                    borderRadius: const BorderRadius.all(
                      Radius.circular(50),
                    ),
                  ),
                  child: Text('${_movie.rating} IMDB',
                      style: const TextStyle(
                        color: Colors.white,
                        fontFamily: 'sf',
                        fontSize: 12,
                      )),
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }
}
