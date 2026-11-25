import 'package:material_ui/material_ui.dart';
import 'package:iconsax_flutter/iconsax_flutter.dart';

import '../theme/app_theme.dart';
import '../utils/image_url.dart';

/// Image that accepts either a bundled asset path (`assets/...`) or a
/// remote URL; failures show a muted placeholder instead of crashing the card.
///
/// Remote images use Flutter's own network image (in-memory cache). They no
/// longer go through cached_network_image: its disk cache asks
/// path_provider for a folder, and on phones where path_provider's JNI layer
/// is broken EVERY remote image silently fell back to the placeholder
/// (posters, extension logos, everything).
class AppImage extends StatelessWidget {
  const AppImage({
    super.key,
    required this.src,
    this.fit = BoxFit.cover,
    this.width,
    this.height,
  });

  /// Asset path (`assets/artwork/...`) or http(s) URL. Empty/null-safe
  /// callers should pass a non-empty string; empty shows the placeholder.
  final String src;
  final BoxFit fit;
  final double? width;
  final double? height;

  @override
  Widget build(BuildContext context) {
    if (src.isEmpty) return _placeholder(context);
    if (src.startsWith('assets/')) {
      return Image.asset(
        src,
        fit: fit,
        width: width,
        height: height,
        errorBuilder: (_, _, _) => _placeholder(context),
      );
    }
    final url = normalizeRemoteImageUrl(src);
    if (url == null) return _placeholder(context);
    return Image.network(
      url,
      fit: fit,
      width: width,
      height: height,
      headers: remoteImageHeaders(url),
      gaplessPlayback: true,
      loadingBuilder: (ctx, child, progress) =>
          progress == null ? child : _placeholder(ctx),
      errorBuilder: (_, _, _) => _placeholder(context),
    );
  }

  Widget _placeholder(BuildContext context) {
    final palette = context.palette;
    return Container(
      width: width,
      height: height,
      color: palette.card,
      alignment: Alignment.center,
      child: Icon(Iconsax.image_copy, size: 32, color: palette.muted),
    );
  }
}
