import pyglet

window = pyglet.window.Window()
context = window.context
config = context.config
config.double_buffer


display = pyglet.canvas.get_display()

