// Pure helpers for remote artwork (no Flutter): URL clean-up and the
// request headers that get past simple hot-link protection.

/// Make a provider-supplied image address loadable, or null if it cannot be.
///
/// * `//cdn.example/x.jpg` (protocol-relative) becomes https.
/// * plain `http://` is upgraded: Android blocks cleartext traffic.
/// * anything that is not an absolute http(s) URL with a host is rejected.
String? normalizeRemoteImageUrl(String src) {
  var s = src.trim();
  if (s.isEmpty) return null;
  if (s.startsWith('//')) s = 'https:$s';
  if (s.startsWith('http://')) s = 'https://${s.substring(7)}';
  final uri = Uri.tryParse(s);
  if (uri == null || !uri.isScheme('https') || uri.host.isEmpty) return null;
  return s;
}

const String _browserUserAgent =
    'Mozilla/5.0 (Linux; Android 13; Pixel 7) AppleWebKit/537.36 '
    '(KHTML, like Gecko) Chrome/125.0.0.0 Mobile Safari/537.36';

/// Headers for fetching [url]: a browser User-Agent and the image's own
/// origin as Referer. Most hot-link rules only reject an empty or foreign
/// Referer, and an image CDN never minds its own origin.
Map<String, String> remoteImageHeaders(String url) {
  final uri = Uri.tryParse(url);
  return {
    'User-Agent': _browserUserAgent,
    if (uri != null && uri.host.isNotEmpty) 'Referer': '${uri.scheme}://${uri.host}/',
  };
}
