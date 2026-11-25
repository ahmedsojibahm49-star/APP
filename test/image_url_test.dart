import 'package:flutter_test/flutter_test.dart';
import 'package:streambox/utils/image_url.dart';

void main() {
  test('protocol-relative and http urls become https', () {
    expect(normalizeRemoteImageUrl('//cdn.example/a.jpg'),
        'https://cdn.example/a.jpg');
    expect(normalizeRemoteImageUrl('http://cdn.example/a.jpg'),
        'https://cdn.example/a.jpg');
    expect(normalizeRemoteImageUrl('  https://cdn.example/a.jpg '),
        'https://cdn.example/a.jpg');
  });

  test('unusable addresses are rejected', () {
    for (final bad in ['', '   ', 'a.jpg', '/images/a.jpg', 'data:image/png;base64,xx', 'ftp://x/a.jpg', 'https:///a.jpg']) {
      expect(normalizeRemoteImageUrl(bad), isNull, reason: bad);
    }
  });

  test('headers carry a browser UA and the image origin as Referer', () {
    final h = remoteImageHeaders('https://cdn.example/path/a.jpg?x=1');
    expect(h['Referer'], 'https://cdn.example/');
    expect(h['User-Agent'], startsWith('Mozilla/5.0'));
  });
}
