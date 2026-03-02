document.addEventListener('DOMContentLoaded', function () {
    const gallery = document.getElementById('gallery');
    if (!gallery) return;

    const links = gallery.querySelectorAll('a[href]');
    if (links.length === 0) return;

    // Create lightbox overlay
    const overlay = document.createElement('div');
    overlay.className = 'announce-lightbox';
    overlay.style.cssText = 'display:none;position:fixed;inset:0;z-index:9999;background:rgba(0,0,0,.85);align-items:center;justify-content:center;cursor:pointer;';

    const img = document.createElement('img');
    img.style.cssText = 'max-width:90vw;max-height:90vh;border-radius:.5rem;box-shadow:0 0 2rem rgba(0,0,0,.5);';
    overlay.appendChild(img);

    const caption = document.createElement('div');
    caption.style.cssText = 'position:absolute;bottom:1.5rem;left:50%;transform:translateX(-50%);color:#fff;font-size:.9rem;text-align:center;';
    overlay.appendChild(caption);

    // Navigation arrows for multiple images
    if (links.length > 1) {
        const arrowStyle = 'position:absolute;top:50%;transform:translateY(-50%);color:#fff;font-size:2rem;background:rgba(0,0,0,.4);border:none;padding:.5rem 1rem;cursor:pointer;border-radius:.25rem;';
        const prev = document.createElement('button');
        prev.style.cssText = arrowStyle + 'left:1rem;';
        prev.textContent = '\u276E';
        prev.type = 'button';
        const next = document.createElement('button');
        next.style.cssText = arrowStyle + 'right:1rem;';
        next.textContent = '\u276F';
        next.type = 'button';
        overlay.appendChild(prev);
        overlay.appendChild(next);

        prev.addEventListener('click', function (e) { e.stopPropagation(); navigate(-1); });
        next.addEventListener('click', function (e) { e.stopPropagation(); navigate(1); });
    }

    document.body.appendChild(overlay);

    let currentIndex = 0;

    function show(index) {
        currentIndex = (index + links.length) % links.length;
        var link = links[currentIndex];
        img.src = link.href;
        caption.textContent = link.title || '';
        overlay.style.display = 'flex';
    }

    function navigate(direction) {
        show(currentIndex + direction);
    }

    links.forEach(function (link, i) {
        link.addEventListener('click', function (e) {
            e.preventDefault();
            show(i);
        });
    });

    overlay.addEventListener('click', function () {
        overlay.style.display = 'none';
    });

    document.addEventListener('keydown', function (e) {
        if (overlay.style.display === 'none') return;
        if (e.key === 'Escape') overlay.style.display = 'none';
        if (e.key === 'ArrowLeft') navigate(-1);
        if (e.key === 'ArrowRight') navigate(1);
    });
});
