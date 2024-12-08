import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../account/account_page.dart';
import '../explore/explore_page.dart';
import '../music/music_page.dart';
import 'bloc/home_page_bloc.dart';

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  final List<Widget> _pages = [
    const KeepAlivePage(child: ExplorePage()),
    const KeepAlivePage(child: MusicPage()),
    const KeepAlivePage(child: AccountPage()),
  ];

  @override
  Widget build(BuildContext context) {
    return BlocBuilder<HomePageBloc, HomePageState>(
      buildWhen: (previous, current) => current is HomePageSwitchTabState,
      builder: (context, state) {
        int selectedIndex = 0;
        if (state is HomePageSwitchTabState) {
          selectedIndex = state.index;
        }
        return Scaffold(
          body: Stack(
            children: [
              IndexedStack(
                index: selectedIndex,
                children: _pages,
              ),
              Align(
                alignment: Alignment.bottomCenter,
                child: _buildBottomNavigationBar(selectedIndex),
              ),
            ],
          ),
        );
      },
    );
  }

  Widget _buildBottomNavigationBar(int selectedIndex) {
    return SafeArea(
      child: BottomNavigationBar(
        currentIndex: selectedIndex,
        onTap: (index) {
          context.read<HomePageBloc>().add(HomeSwitchTabEvent(index));
        },
        backgroundColor: Colors.transparent,
        elevation: 0,
        selectedItemColor: Colors.white,
        unselectedItemColor: Colors.white70,
        items: const [
          BottomNavigationBarItem(icon: Icon(Icons.search), label: "发现"),
          BottomNavigationBarItem(icon: Icon(Icons.music_note), label: "音乐"),
          BottomNavigationBarItem(icon: Icon(Icons.person), label: "我的"),
        ],
      ),
    );
  }
}

class KeepAlivePage extends StatefulWidget {
  final Widget child;

  const KeepAlivePage({
    super.key,
    required this.child,
  });

  @override
  State<KeepAlivePage> createState() => _KeepAlivePageState();
}

class _KeepAlivePageState extends State<KeepAlivePage>
    with AutomaticKeepAliveClientMixin {
  @override
  bool get wantKeepAlive => true;

  @override
  Widget build(BuildContext context) {
    super.build(context);
    return widget.child;
  }
}
