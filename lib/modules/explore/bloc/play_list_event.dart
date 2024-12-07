sealed class PlayListEvent {}

class RequestHotPlayListEvent extends PlayListEvent {}

class RequestHighQualityPlayListEvent extends PlayListEvent {
  final String cat;
  final int limit;

  RequestHighQualityPlayListEvent({this.cat = "全部", this.limit = 10});
}

class RequestPlayListRecommendEvent extends PlayListEvent {}

class RequestHighQualityTagsEvent extends PlayListEvent {}

class RequestTopArtistsEvent extends PlayListEvent {}

class RequestPlayListDetailEvent extends PlayListEvent {
  final int id;

  RequestPlayListDetailEvent({required this.id});
}
