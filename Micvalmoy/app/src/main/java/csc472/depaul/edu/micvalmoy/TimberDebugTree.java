package csc472.depaul.edu.micvalmoy;

//** https://android.jlelse.eu/my-timber-setup-493a8ec7a10c


import timber.log.Timber;

public class TimberDebugTree extends Timber.DebugTree {

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
    super.log(priority, tag, message, t);
}

    @Override
    protected String createStackElementTag(StackTraceElement element) {
        // Add log statements line number to the log
        return super.createStackElementTag(element) + " - " + element.getLineNumber();
    }
}