# Introduction #

If you are a GWT developer chances are you came across [Mvp sample project](http://code.google.com/webtoolkit/doc/latest/tutorial/mvp-architecture.html) and [Ray Ryan Mvp google/io presentation](http://code.google.com/events/io/2009/sessions/GoogleWebToolkitBestPractices.html).

I'd caution against following the mentioned word by word. Use event bus and command pattern rpc sparingly i.e. it should not be a knee jerk reaction.

# Different flavors of Model View Presenter #

Basically there is what I call the "Google" style MVP as this is presenter in already mentioned gwt sample project and Ray Ryan's talk. It's point is that the View doesn't have a reference to the Presenter at least this is how they presented it. The presenter installs it's listeners via the HasClickHandlers. Also the View needs to have asWidget() method which I find rather nasty.

The alternative is what I call .Net style MVP. This is the style you can find in various .Net turotial and blog posts and is used even before Gwt 1.0. Basically both the View and Presenter have reference to each other. In theory this sounds worse but in practice I find this approach better.

Both ways are presenter in-dept in the corresponding articles.

# History handling #

Todo: Same functionality less fuss

# How to use the event bus #

# How to do layouting #

# Going beyond stock widgets #

# What about gwt generators #