package org.trades.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks the fields that will be autowired by the dependency injection
 * facilities.
 *
 * @author Brighton Kukasira <brighton.kukasira@gmail.com>
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Autowired {

}
