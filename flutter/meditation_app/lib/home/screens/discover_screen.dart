import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:meditation_app/home/widgets/label_card.dart';
import 'package:meditation_app/home/widgets/recent_card.dart';
import 'package:meditation_app/home/widgets/recommended_card.dart';

import '../models/recent_model.dart';
import '../models/recommended_model.dart';

class DiscoverScreen extends StatelessWidget {
  const DiscoverScreen({Key? key}) : super(key: key);

  static const List<String> labels = ['Insomnia', 'Depression', 'Baby Sleep'];
  static const List<RecentModel> recentModels = [
    RecentModel('Calming Sounds', 'icons/headphone.svg'),
    RecentModel('Insomnia', 'icons/tape.svg'),
    RecentModel('For Children', 'icons/heart.svg'),
    RecentModel('Tips for sleeping', 'icons/tape.svg'),
  ];
  static const List<RecommendedModel> recommendedModels = [
    RecommendedModel('Sleep Meditation', '7 Day Audio Series',
        ['icons/headphone.svg', 'icons/tape.svg']),
    RecommendedModel('For Better Sleep', '1 Month Audio Series',
        ['icons/heart.svg', 'icons/tape.svg']),
  ];

  @override
  Widget build(BuildContext context) {
    final query = MediaQuery.of(context);

    return SingleChildScrollView(
      child: Container(
        margin: const EdgeInsets.only(top: 30),
        padding: const EdgeInsets.all(20),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Row(
              crossAxisAlignment: CrossAxisAlignment.center,
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                const Text(
                  'Discover',
                  style: TextStyle(
                    color: Colors.white,
                    fontSize: 32,
                    fontFamily: 'sf',
                    fontWeight: FontWeight.w600,
                  ),
                ),
                IconButton(
                  onPressed: () {},
                  icon: SvgPicture.asset('icons/search.svg'),
                ),
              ],
            ),
            Divider(
              color: const Color.fromRGBO(74, 128, 240, 1.0),
              thickness: 3,
              endIndent: query.size.width * 0.8,
            ),
            const SizedBox(
              height: 20,
            ),
            SizedBox(
              height: 50,
              child: ListView.builder(
                itemBuilder: (context, index) =>
                    LabelCard(labels.elementAt(index), index == 0),
                scrollDirection: Axis.horizontal,
                itemCount: labels.length,
                shrinkWrap: true,
              ),
            ),
            const SizedBox(
              height: 30,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: const [
                Text(
                  'Recommended',
                  style: TextStyle(
                    color: Color.fromRGBO(81, 89, 121, 1.0),
                    fontFamily: 'sf',
                  ),
                ),
                Text(
                  'See All',
                  style: TextStyle(
                    color: Color.fromRGBO(74, 128, 240, 1.0),
                    fontFamily: 'sf',
                  ),
                ),
              ],
            ),
            const SizedBox(
              height: 20,
            ),
            SizedBox(
              height: 200,
              child: ListView.builder(
                scrollDirection: Axis.horizontal,
                itemBuilder: (context, index) => RecommendedCard(index, recommendedModels.elementAt(index)),
                itemCount: recommendedModels.length,
                shrinkWrap: true,
              ),
            ),
            const SizedBox(
              height: 30,
            ),
            const Text(
              'Recent',
              style: TextStyle(
                color: Color.fromRGBO(81, 89, 121, 1.0),
                fontFamily: 'sf',
              ),
            ),
            const SizedBox(
              height: 20,
            ),
            SizedBox(
              height: (query.size.height - query.padding.top) * 0.3,
              child: GridView.builder(
                padding: EdgeInsets.zero,
                gridDelegate: const SliverGridDelegateWithMaxCrossAxisExtent(
                  maxCrossAxisExtent: 200,
                  childAspectRatio: 10 / 8,
                  crossAxisSpacing: 20,
                  mainAxisSpacing: 20,
                ),
                itemBuilder: (context, index) =>
                    RecentCard(index, recentModels.elementAt(index)),
                itemCount: recentModels.length,
              ),
            ),
          ],
        ),
      ),
    );
  }
}
