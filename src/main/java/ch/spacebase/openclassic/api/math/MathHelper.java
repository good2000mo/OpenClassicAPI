package ch.spacebase.openclassic.api.math;

/**
 * A math utility class.
 */
public class MathHelper {

	public static final float f_2PI = (float) (2.0d * Math.PI);
	public static final float DEG_TO_RAD = 0.01745329f;
	private static float SIN[] = new float[65536];

	static {
		for (int i = 0; i < 65536; i++) {
			SIN[i] = (float) Math.sin((i * Math.PI * 2D) / 65536D);
		}
	}

	public static float sin(float f) {
		return SIN[(int) (f * 10430.38F) & 0xffff];
	}

	public static float cos(float f) {
		return SIN[(int) (f * 10430.38F + 16384F) & 0xffff];
	}
	
	public static Vector toForwardVec(float yaw, float pitch) {
		float xzLen = MathHelper.cos(-pitch * DEG_TO_RAD);
		float x = (MathHelper.sin(-yaw * DEG_TO_RAD - (float) Math.PI) * xzLen);
		float y = MathHelper.sin(-pitch * DEG_TO_RAD);
		float z = (MathHelper.cos(-yaw * DEG_TO_RAD - (float) Math.PI) * xzLen);
		return new Vector(x, y, z);
	}
	
	/**
	 * Casts the given object to an integer if applicable.
	 * @param o Object to cast.
	 * @return The resulting integer.
	 */
	public static Integer castInt(Object o) {
		if (o instanceof Number) {
			return ((Number) o).intValue();
		}

		return null;
	}

	/**
	 * Casts the given object to a double if applicable.
	 * @param o Object to cast.
	 * @return The resulting double.
	 */
	public static Double castDouble(Object o) {
		if (o instanceof Number) {
			return ((Number) o).doubleValue();
		}

		return null;
	}

	/**
	 * Casts the given object to a float if applicable.
	 * @param o Object to cast.
	 * @return The resulting float.
	 */
	public static Float castFloat(Object obj) {
		if (obj instanceof Number) {
			return ((Number) obj).floatValue();
		}

		return null;
	}

	/**
	 * Casts the given object to a boolean if applicable.
	 * @param o Object to cast.
	 * @return The resulting boolean.
	 */
	public static Boolean castBoolean(Object o) {
		if (o instanceof Boolean) {
			return (Boolean) o;
		} else if (o instanceof String) {
			try {
				return Boolean.parseBoolean((String) o);
			} catch (IllegalArgumentException e) {
				return null;
			}
		}

		return null;
	}

}
