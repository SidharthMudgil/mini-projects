import 'package:flutter/material.dart';
import 'package:flutter_svg/flutter_svg.dart';
import 'package:meditation_app/home/screens/chart_screen.dart';
import 'package:meditation_app/home/screens/discover_screen.dart';
import 'package:meditation_app/home/screens/profile_screen.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  int _selectedPage = 0;
  late PageController _pageController;

  @override
  void initState() {
    super.initState();
    _pageController = PageController(initialPage: _selectedPage);
  }

  void _onTap(int page) {
    _pageController.animateToPage(
      page,
      duration: const Duration(milliseconds: 300),
      curve: Curves.easeInOut,
    );
  }

  void _onPageChanged(int page) {
    setState(() {
      _selectedPage = page;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: PageView(
        controller: _pageController,
        onPageChanged: _onPageChanged,
        children: const [DiscoverScreen(), ChartScreen(), ProfileScreen()],
      ),
      bottomNavigationBar: BottomNavigationBar(
        type: BottomNavigationBarType.fixed,
        showSelectedLabels: false,
        showUnselectedLabels: false,
        backgroundColor: const Color.fromRGBO(28, 32, 49, 1.0),
        selectedItemColor: const Color.fromRGBO(255, 143, 0, 1.0),
        currentIndex: _selectedPage,
        items: [
          BottomNavigationBarItem(
            icon: SvgPicture.asset(
              'icons/discover.svg',
            ),
            activeIcon: SvgPicture.asset(
              'icons/discover.svg',
              color: const Color.fromRGBO(74, 128, 240, 1.0),
            ),
            label: '',
          ),
          BottomNavigationBarItem(
            icon: SvgPicture.asset(
              'icons/chart.svg',
            ),
            activeIcon: SvgPicture.asset(
              'icons/chart.svg',
              color: const Color.fromRGBO(74, 128, 240, 1.0),
            ),
            label: '',
          ),
          BottomNavigationBarItem(
            icon: SvgPicture.asset(
              'icons/profile.svg',
            ),
            activeIcon: SvgPicture.asset(
              'icons/profile.svg',
              color: const Color.fromRGBO(74, 128, 240, 1.0),
            ),
            label: '',
          ),
        ],
        onTap: (page) => _onTap(page),
      ),
    );
  }
}
