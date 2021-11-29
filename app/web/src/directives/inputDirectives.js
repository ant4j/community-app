export function blurOnEnter(node) {

	const handleKey = event => {
		if (event.keyCode == 13 && node && typeof node.blur === 'function') {
			node.blur();
		}
	}

	node.addEventListener('keydown', handleKey)

	return {
		destroy() {
			node.removeEventListener('keydown', handleKey)
		}
	}
}