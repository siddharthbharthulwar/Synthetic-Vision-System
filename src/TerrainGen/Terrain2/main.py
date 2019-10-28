import pyglet

window = pyglet.window.Window(width = 800, height = 600, caption = 'Pyglet', resizable = True)
window.set_exclusive_mouse(False)
pyglet.app.run()