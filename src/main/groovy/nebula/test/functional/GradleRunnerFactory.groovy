package nebula.test.functional;

import nebula.test.functional.internal.DefaultGradleRunner;
import nebula.test.functional.internal.GradleHandleFactory;
import nebula.test.functional.internal.classpath.ClasspathInjectingGradleHandleFactory;
import nebula.test.functional.internal.launcherapi.LauncherGradleHandleFactory;
import nebula.test.functional.internal.toolingapi.ToolingApiGradleHandleFactory;

public class GradleRunnerFactory {

    // TODO Easier way to define which implementation to use
    public static GradleRunner createTooling() {
        GradleHandleFactory toolingApiHandleFactory = new ToolingApiGradleHandleFactory();

        return create(toolingApiHandleFactory);
    }

    public static GradleRunner createLauncher() {
        GradleHandleFactory launcherHandleFactory = new LauncherGradleHandleFactory();

        return create(launcherHandleFactory);
    }

    public static GradleRunner create(GradleHandleFactory handleFactory) {

        // TODO: Which class would be attached to the right classloader? Is using something from the test kit right?
        ClassLoader sourceClassLoader = GradleRunnerFactory.class.getClassLoader();
        GradleHandleFactory classpathInjectingHandleFactory = new ClasspathInjectingGradleHandleFactory(sourceClassLoader, handleFactory);

        return new DefaultGradleRunner(classpathInjectingHandleFactory);
    }

}
